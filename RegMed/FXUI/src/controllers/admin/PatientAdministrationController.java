package controllers.admin;

import database.MyBatisDbConnection;
import entities.Patient;
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
import javafx.util.Callback;
import mappers.PatientAdministrationMapper;
import models.PatientAdministrationDTO;
import pojo.Address;
import repositories.PatientRepository;
import repositories.RepositoryInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
            addressField,
            forenameFieldAdd,
            nameFieldAdd,
            peselFieldAdd,
            addressFieldAdd;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editPatientTab,
            createPatientTab;

    @FXML
    private TableView<pojo.Patient> patientsTable;
    @FXML
    private TableColumn<pojo.Patient, Integer> idColumn,
            firstContactDoctorIdColumn;
    @FXML
    private TableColumn<pojo.Patient, String> forenameColumn,
            nameColumn,
            peselColumn,
            addressColumn,
            emailColumn;


    private RepositoryInterface<Patient> patientRepository;
    private PatientAdministrationDTO patientAdministrationDTO;

    private FilteredList filteredList;

    ObservableList<pojo.Patient> tableData;

    public PatientAdministrationController() {
        this.patientRepository = new PatientRepository();

        patientAdministrationDTO = new PatientAdministrationDTO();
    }

    @FXML
    private void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<pojo.Patient, Integer>("patientId"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<pojo.Patient, String>("firstName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<pojo.Patient, String>("lastName"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<pojo.Patient, String>("pesel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<pojo.Patient, String>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<pojo.Patient, String>("email"));
        firstContactDoctorIdColumn.setCellValueFactory(new PropertyValueFactory<pojo.Patient, Integer>("firstContactDoctorId"));

        //load data
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
            else if (patient.getName().toLowerCase().contains(newValue.toLowerCase())) {
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

            pojo.Patient patientToEdit = getSelectedPatientInTable();
            fillEditPatientFields(patientToEdit);

            saveEditButton.setOnAction(e -> {
                //patientRepository.update(createPatientForEditFromTextfields(patientToEdit));

                patientAdministrationDTO.update(patientToEdit);
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
        pojo.Patient patientToAdd = new pojo.Patient();
        pojo.Address addressToAdd= new Address();

        addressToAdd.setCity("city");
        addressToAdd.setZip("zip");
        addressToAdd.setStreet("street");
        addressToAdd.setNumber(1);

        patientToAdd.setFirstName(forenameFieldAdd.getText());
        patientToAdd.setLastName(nameFieldAdd.getText());
        patientToAdd.setPesel(peselFieldAdd.getText());
        patientToAdd.setAddress(addressToAdd);
        patientToAdd.setEmail("email@mail.com");
        patientToAdd.setPhoneNumber("665554223");
        patientToAdd.setFirstContactDoctorId(1);
        patientToAdd.setPassword(peselFieldAdd.getText());

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
        //(int id, String forename, String name, String password, String pesel, String address)
        Patient patientToReturn = new Patient(
                patient.getId(),
                forenameField.getText(),
                nameField.getText(),
                patient.getPassword(),
                peselField.getText(),
                addressField.getText()
        );
        return patientToReturn;
    }


    private void fillEditPatientFields(pojo.Patient patient) {
        peselField.setText(patient.getPesel());
        forenameField.setText(patient.getFirstName());
        nameField.setText(patient.getLastName());
        addressField.setText(patient.getAddress().toString());
    }

    private void clearAddPatientFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        addressFieldAdd.setText(null);
    }





    //HELPER METHODS

    private pojo.Patient getSelectedPatientInTable() {
        return patientsTable.getSelectionModel().getSelectedItem();
    }

    private void editTabDisable(boolean bool) {
        editPatientTab.setDisable(bool);
    }


}
