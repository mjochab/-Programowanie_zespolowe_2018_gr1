package controllers.admin;

import dto.DoctorAdministrationDTO;
import exceptions.ValidationException;
import pojo.Address;
import pojo.Doctor;
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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

public class DoctorAdministrationController implements ControllerPagination {

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
            specializationField,

            forenameFieldAdd,
            nameFieldAdd,
            peselFieldAdd,
            emailFieldAdd,
            phoneFieldAdd,
            cityFieldAdd,
            zipFieldAdd,
            streetFieldAdd,
            numberFieldAdd,
            specializationFieldAdd;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editDoctorTab,
            createDoctorTab;

    @FXML
    private TableView<Doctor> doctorsTable;
    @FXML
    private TableColumn<Doctor, Integer> idColumn,
            specializationColumn;
    @FXML
    private TableColumn<Doctor, String> forenameColumn,
            nameColumn,
            peselColumn,
            emailColumn,
            phoneNumberColumn,
            addressColumn;

    @FXML
    private Tab editWorkingHoursTab;

    private DoctorAdministrationDTO doctorAdministrationDTO;

    private FilteredList filteredList;

    ObservableList<Doctor> tableData;

    public DoctorAdministrationController() {
        this.doctorAdministrationDTO = new DoctorAdministrationDTO();
    }

    @FXML
    private void initialize() {

        //setup columns in the table
        idColumn.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("id"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("firstName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lastName"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("pesel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("address"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("specializationId"));

        //load data
        loadDataToTable();

        filteredList = new FilteredList(tableData, e->true);    //list using to filter data


        editTabDisable(true);
    }

    private void loadDataToTable() {

        //if (tableData != null)
        //    tableData.removeAll();

        tableData = FXCollections.observableArrayList(doctorAdministrationDTO.getAll());
        doctorsTable.setItems(tableData);
        filteredList = new FilteredList(tableData, e->true);
        doctorsTable.refresh();
    }


    @FXML
    private void searchDoctorsByPeselAndName(KeyEvent event) {
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate((Predicate<? super Doctor>) (Doctor doctor)->{
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                else if (doctor.getLastName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                else if (doctor.getPesel().contains(newValue)) {
                    return true;
                }
                return false;
            });
        }));

        SortedList sort = new SortedList(filteredList);
        sort.comparatorProperty().bind(doctorsTable.comparatorProperty());
        doctorsTable.setItems(sort);
    }



    //--ACTION_METHODS--



    @FXML
    private void removeDoctorClicked(ActionEvent event) {
        Doctor doctorToDelete = getSelectedDoctorInTable();

        if(getSelectedDoctorInTable() != null) {
            if ( DialogBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
                    doctorToDelete.getFirstName(), doctorToDelete.getLastName()), "Are you sure?") ) {

                doctorAdministrationDTO.delete(getSelectedDoctorInTable().getId());
                loadDataToTable();
            }
        } else {
            DialogBox.warningBox("Information", "Please select doctor to remove in table");
        }
    }

    @FXML
    private void editDoctorClicked(ActionEvent event) {
        if(getSelectedDoctorInTable() != null) {

            editTabDisable(false);
            tabPane.getSelectionModel().select(editDoctorTab);

            Doctor doctorToEdit = getSelectedDoctorInTable();
            fillEditDoctorFields(doctorToEdit);
            fillEditDoctorAddressFields(doctorToEdit.getAddress());

            saveEditButton.setOnAction(e -> {
                doctorAdministrationDTO.update(createDoctorForEditFromTextfields(doctorToEdit));
                doctorAdministrationDTO.updateAddress(
                        createAddressForEditFromTexfields(doctorToEdit.getAddress()));
                doctorAdministrationDTO.updateSpecialization(
                        doctorToEdit, Integer.parseInt(specializationField.getText()));

                loadDataToTable();
                editTabDisable(true);
                tabPane.getSelectionModel().select(createDoctorTab);
            });

            declineEditButton.setOnAction(e -> {
                editTabDisable(true);
                tabPane.getSelectionModel().select(createDoctorTab);
            });
        } else {
            DialogBox.warningBox("Information", "Please select doctor to edit in table");
        }
    }

    @FXML
    private void createDoctorClicked(ActionEvent event) {
        Doctor doctorToAdd = new Doctor();
        Address addressToAdd = new Address();
        try {
            addressToAdd.setCity(cityFieldAdd.getText());
            addressToAdd.setZip(zipFieldAdd.getText());
            addressToAdd.setStreet(streetFieldAdd.getText());
            if (numberFieldAdd.getText().isEmpty()) {
                throw new ValidationException("Missing address number");
            } else {
                addressToAdd.setNumber(Integer.parseInt(numberFieldAdd.getText()));
            }

            doctorToAdd.setFirstName(forenameFieldAdd.getText());
            doctorToAdd.setLastName(nameFieldAdd.getText());
            doctorToAdd.setPesel(peselFieldAdd.getText());
            doctorToAdd.setAddress(addressToAdd);
            doctorToAdd.setEmail(emailFieldAdd.getText().toLowerCase());
            doctorToAdd.setPhoneNumber(phoneFieldAdd.getText());
            if (specializationFieldAdd.getText().isEmpty()) {
                throw new ValidationException("Missing specialization id");
            } else {
                doctorToAdd.setSpecializationId(Integer.parseInt(specializationFieldAdd.getText()));
            }

            doctorToAdd.setPassword(nameFieldAdd.getText());


            doctorAdministrationDTO.add(doctorToAdd);
            loadDataToTable();
            //clearAddDoctorFields();
        }
        catch (ValidationException ex) {
            DialogBox.validationErrorBox("Wrong doctor data", ex.getMessage());
        }




    }

    @FXML
    private void clearDoctorClickedAdd(ActionEvent event) {
        clearAddDoctorFields();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/AdminHome", event);
    }

    private Doctor createDoctorForEditFromTextfields(Doctor doctor) {
        doctor.setFirstName(forenameField.getText());
        doctor.setLastName(nameField.getText());
        doctor.setPesel(peselField.getText());
        doctor.setEmail(emailField.getText());
        doctor.setPhoneNumber(phoneField.getText());
        doctor.setSpecializationId(Integer.parseInt(specializationField.getText()));
        return doctor;
    }

    private Address createAddressForEditFromTexfields(Address address) {
        Address addressToReturn = address;

        addressToReturn.setCity(cityField.getText());
        addressToReturn.setZip(zipField.getText());
        addressToReturn.setStreet(streetField.getText());
        addressToReturn.setNumber(Integer.parseInt(String.valueOf(numberField.getText())));

        return addressToReturn;
    }


    private void fillEditDoctorFields(Doctor doctor) {
        peselField.setText(doctor.getPesel());
        forenameField.setText(doctor.getFirstName());
        nameField.setText(doctor.getLastName());
        peselField.setText(doctor.getPesel());
        emailField.setText(doctor.getEmail());
        phoneField.setText(doctor.getPhoneNumber());
        specializationField.setText(String.valueOf(doctor.getSpecializationId()));
    }

    private void fillEditDoctorAddressFields(Address address) {
        cityField.setText(address.getCity());
        zipField.setText(address.getZip());
        streetField.setText(address.getStreet());
        numberField.setText(String.valueOf(address.getNumber()));
    }

    private void clearAddDoctorFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        emailFieldAdd.setText(null);
        phoneFieldAdd.setText(null);

        cityFieldAdd.setText(null);
        zipFieldAdd.setText(null);
        streetFieldAdd.setText(null);
        numberFieldAdd.setText(null);

        specializationFieldAdd.setText(null);
    }





    //HELPER METHODS

    private Doctor getSelectedDoctorInTable() {
        return doctorsTable.getSelectionModel().getSelectedItem();
    }

    private void editTabDisable(boolean bool) {
        editDoctorTab.setDisable(bool);
    }

}
