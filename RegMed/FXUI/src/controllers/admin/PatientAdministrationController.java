package controllers.admin;

import helpers.ControllerPagination;
import helpers.DialogBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import dto.PatientAdministrationDTO;
import pojo.Address;
import pojo.Administrator;
import pojo.Patient;

import java.io.IOException;


public class PatientAdministrationController implements ControllerPagination {

    @FXML
    private Button saveEditButton,
            declineEditButton;

    @FXML
    private TextField idField,
            searchField,
            peselField,
            forenameField,
            nameField,
            emailField,
            phoneField,
            cityField,
            zipField,
            streetField,
            numberField,
            doctorIdField,

            forenameFieldAdd,
            nameFieldAdd,
            peselFieldAdd,
            emailFieldAdd,
            phoneFieldAdd,
            cityFieldAdd,
            zipFieldAdd,
            streetFieldAdd,
            numberFieldAdd,
            doctorIdFieldAdd;   //TODO: change to dropdown with dload data from doctors


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editPatientTab,
            createPatientTab;

    @FXML
    private TableView<Patient> patientsTable;
    @FXML
    private TableColumn<Patient, Integer> idColumn,
            firstContactDoctorIdColumn;
    @FXML
    private TableColumn<Patient, String> forenameColumn,
            nameColumn,
            peselColumn,
            addressColumn,
            emailColumn;

    
    private PatientAdministrationDTO patientAdministrationDTO;

    private FilteredList filteredList;
    public static Administrator administrator;
    ObservableList<Patient> tableData;

    public PatientAdministrationController() {
        patientAdministrationDTO = new PatientAdministrationDTO();
    }

    @FXML
    private void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("patientId"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("pesel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
        firstContactDoctorIdColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("firstContactDoctorId"));

        loadDataToTable();

        filteredList = new FilteredList(tableData, e->true);    //list using to filter data


        editTabDisable(true);
    }

    private void loadDataToTable() {

        //if (tableData != null)
        //    tableData.removeAll();

        //tableData = FXCollections.observableArrayList(patientRepository.getAll());
        tableData = FXCollections.observableArrayList(patientAdministrationDTO.getAll());

        patientsTable.setItems(tableData);
        filteredList = new FilteredList(tableData, e->true);
        patientsTable.refresh();
    }



    //--ACTION_METHODS--




    @FXML
    private void searchPatientsByPeselAndName(KeyEvent event) {
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
        filteredList.setPredicate((java.util.function.Predicate<? super Patient>) (Patient patient)->{
            if (newValue.isEmpty() || newValue == null) {
                return true;
            }
            else if (patient.getLastName().toLowerCase().contains(newValue.toLowerCase())) {
                return true;
            }
            else if (patient.getPesel().contains(newValue)) {
                return true;
            }
            return false;
        });
        }));

        //Seting sorted items in patients table
        SortedList sort = new SortedList(filteredList);
        sort.comparatorProperty().bind(patientsTable.comparatorProperty());
        patientsTable.setItems(sort);
    }


    @FXML
    private void removePatientClicked(ActionEvent event) {
//        Patient patientToDelete = getSelectedPatientInTable();
//
//        if (getSelectedPatientInTable() != null) {
//            if ( DialogBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
//                    patientToDelete.getForename(), patientToDelete.getName()), "Are you sure?") ) {
//
//                patientRepository.remove(patientToDelete);
//
//                loadDataToTable();
//            }
//        } else {
//            DialogBox.warningBox("Information", "Please select patient to remove in table");
//        }
    }

    @FXML
    private void editPatientClicked(ActionEvent event) {
        if (getSelectedPatientInTable() != null) {

            editTabDisable(false);
            tabPane.getSelectionModel().select(editPatientTab);

            Patient patientToEdit = getSelectedPatientInTable();
            fillEditPatientFields(patientToEdit);
            fillEditPatientAddressFields(patientToEdit.getAddress());

            saveEditButton.setOnAction(e -> {
                patientAdministrationDTO.update(createPatientForEditFromTextfields(patientToEdit));
                patientAdministrationDTO.updateAddress(createAddressForEditFromTexfields(patientToEdit.getAddress()));
                patientAdministrationDTO.updateFirstcontactDoctorId(patientToEdit, Integer.parseInt(doctorIdField.getText()));

                loadDataToTable();
                editTabDisable(true);
                tabPane.getSelectionModel().select(createPatientTab);
            });

            declineEditButton.setOnAction(e -> {
                editTabDisable(true);
                tabPane.getSelectionModel().select(createPatientTab);
            });
        } else {
            DialogBox.warningBox("Information", "Please select patient to edit in table");
        }
    }

    @FXML
    private void createPatientClicked(ActionEvent event) {
        Patient patientToAdd = new Patient();
        Address addressToAdd= new Address();

        addressToAdd.setCity(cityFieldAdd.getText());
        addressToAdd.setZip(zipFieldAdd.getText());
        addressToAdd.setStreet(streetFieldAdd.getText());
        addressToAdd.setNumber(Integer.parseInt(numberFieldAdd.getText()));

        patientToAdd.setFirstName(forenameFieldAdd.getText());
        patientToAdd.setLastName(nameFieldAdd.getText());
        patientToAdd.setPesel(peselFieldAdd.getText());
        patientToAdd.setAddress(addressToAdd);
        patientToAdd.setEmail(emailFieldAdd.getText());
        patientToAdd.setPhoneNumber(phoneFieldAdd.getText());
        patientToAdd.setFirstContactDoctorId(Integer.parseInt(doctorIdFieldAdd.getText()));
        patientToAdd.setPassword(nameFieldAdd.getText());

        patientAdministrationDTO.add(patientToAdd);

        loadDataToTable();
        clearAddPatientFields();
    }

    @FXML
    private void clearPatientClickedAdd(ActionEvent event) {
        clearAddPatientFields();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/AdminHome", event);
    }

    private Patient createPatientForEditFromTextfields(Patient patient) {
        Patient patientToReturn = patient;

        patientToReturn.setFirstName(forenameField.getText());
        patientToReturn.setLastName(nameField.getText());
        patientToReturn.setPesel(peselField.getText());
        patientToReturn.setEmail(emailField.getText());
        patientToReturn.setPhoneNumber(phoneField.getText());
        patientToReturn.setFirstContactDoctorId(Integer.parseInt(doctorIdField.getText()));
        return patientToReturn;
    }

    private Address createAddressForEditFromTexfields(Address address) {
        Address addressToReturn = address;

        addressToReturn.setCity(cityField.getText());
        addressToReturn.setZip(zipField.getText());
        addressToReturn.setStreet(streetField.getText());
        addressToReturn.setNumber(Integer.parseInt(String.valueOf(numberField.getText())));

        return addressToReturn;
    }



    private void fillEditPatientFields(Patient patient) {
        forenameField.setText(patient.getFirstName());
        nameField.setText(patient.getLastName());
        peselField.setText(patient.getPesel());
        emailField.setText(patient.getEmail());
        phoneField.setText(patient.getPhoneNumber());
        doctorIdField.setText(String.valueOf(patient.getFirstContactDoctorId()));
    }

    private void fillEditPatientAddressFields(Address address) {
        cityField.setText(address.getCity());
        zipField.setText(address.getZip());
        streetField.setText(address.getStreet());
        numberField.setText(String.valueOf(address.getNumber()));
    }

    private void clearAddPatientFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        emailFieldAdd.setText(null);
        phoneFieldAdd.setText(null);

        cityFieldAdd.setText(null);
        zipFieldAdd.setText(null);
        streetFieldAdd.setText(null);
        numberFieldAdd.setText(null);

        doctorIdFieldAdd.setText(null);
    }





    //HELPER METHODS

    private Patient getSelectedPatientInTable() {
        return patientsTable.getSelectionModel().getSelectedItem();
    }

    private void editTabDisable(boolean bool) {
        editPatientTab.setDisable(bool);
    }


}
