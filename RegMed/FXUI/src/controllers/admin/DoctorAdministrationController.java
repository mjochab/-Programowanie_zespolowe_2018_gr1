package controllers.admin;

import customControls.*;
import dto.DoctorAdministrationDTO;
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
import pojo.Specialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Doctors administration.
 * Containing crud operations and governing fxml form behavior (inserting values
 * into textfields etc).
 *
 * @see helpers.ControllerPagination is using to hold helpers, mostly for
 *        changing pages.
 * @author Szymon P
 *
 */
public class DoctorAdministrationController implements ControllerPagination {

    @FXML
    private Button saveEditButton,
            declineEditButton;

    @FXML
    private TextField searchField,
            createSpecializationField;

    @FXML
    private ListView<Specialization> removeSpecializationListView;

    @FXML
    private NameTextField forenameField,
            nameField,
            forenameFieldAdd,
            nameFieldAdd;

    @FXML
    private PeselTextField peselField,
            peselFieldAdd;

    @FXML
    private EmailTextField emailField,
            emailFieldAdd;

    @FXML
    private PhoneTextField phoneField,
            phoneFieldAdd;

    @FXML
    private CityTextField cityField,
            cityFieldAdd;

    @FXML
    private ZipTextField zipField,
            zipFieldAdd;

    @FXML
    private StreetTextField streetField,
            streetFieldAdd;

    @FXML
    private NumberTextField numberField,
            numberFieldAdd;




    @FXML
    private ChoiceBox<String> specializationEditChoiceBox;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editDoctorTab,
            createDoctorTab;

    @FXML
    private TableView<Doctor> doctorsTable;
    @FXML
    private TableColumn<Doctor, Integer> idColumn;
    @FXML
    private TableColumn<Doctor, String> forenameColumn,
            nameColumn,
            peselColumn,
            emailColumn,
            phoneNumberColumn,
            addressColumn,
            specializationColumn;

    @FXML
    private Tab editWorkingHoursTab;

    @FXML
    private ChoiceBox<String> specializationChoiceBox;

    private DoctorAdministrationDTO doctorAdministrationDTO;

    private FilteredList filteredList;
    ObservableList<Doctor> tableData;
    private List<Specialization> specializations;
    private boolean[] editionSuccess;

    public DoctorAdministrationController() {
        this.doctorAdministrationDTO = new DoctorAdministrationDTO();
        specializations = FXCollections.observableArrayList(
                doctorAdministrationDTO.getAllSpecializations());
        editionSuccess = new boolean[2];
    }

    /**
     * Setting factory relation between values in table and pojo class(es).
     */
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
        specializationColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));

        //load data
        loadDataToTable();

        filteredList = new FilteredList(tableData, e->true);    //list using to filter data
        editTabDisable(true);

        //specializationChoiceBox.setItems(FXCollections.observableArrayList(specializationsToString()));
        //removeSpecializationListView.setItems(FXCollections.observableArrayList(specializations));
        setSpecializations();

    }

    private void setSpecializations() {
        specializations = FXCollections.observableArrayList(
                doctorAdministrationDTO.getAllSpecializations());
        specializationChoiceBox.setItems(FXCollections.observableArrayList(specializationsToString()));
        removeSpecializationListView.setItems(FXCollections.observableArrayList(specializations));
        loadSpecializationsToChoiceBox();

    }



    /**
     * Loading/refreshing data in table. Also applying filteredList on table
     * from method searchDoctorsByPeselAndName.
     */
    private void loadDataToTable() {

        //if (tableData != null)
        //    tableData.removeAll();

        tableData = FXCollections.observableArrayList(doctorAdministrationDTO.getAll());
        doctorsTable.setItems(tableData);
        filteredList = new FilteredList(tableData, e->true);
        doctorsTable.refresh();
    }


    /**
     * Filtering doctors in table using pesel or name.
     */
    @FXML
    private void filterDoctorsByPeselAndName() {
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


    /**
     * Removing selected in table doctor from database.
     */
    @FXML
    private void removeDoctorClicked() {
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

    /**
     * Editing doctor in database.
     * Downloading selected to edit doctor from database and setting
     * him data specified in doctor object created from textfields.
     */
    @FXML
    private void editDoctorClicked() {

        if(getSelectedDoctorInTable() != null) {

            editTabDisable(false);
            tabPane.getSelectionModel().select(editDoctorTab);

            Doctor doctorToEdit = getSelectedDoctorInTable();
            fillEditDoctorFields(doctorToEdit);
            fillEditDoctorAddressFields(doctorToEdit.getAddress());

            saveEditButton.setOnAction(e -> {

                List<Specialization> specializations = doctorAdministrationDTO.getAllSpecializations();
                int selectedIndex = specializationEditChoiceBox.getSelectionModel().getSelectedIndex();

                doctorAdministrationDTO.update(createDoctorForEditFromTextfields(doctorToEdit));
                doctorAdministrationDTO.updateAddress(
                        createAddressForEditFromTexfields(doctorToEdit.getAddress()));
                doctorAdministrationDTO.updateSpecialization(
                        doctorToEdit,
                        specializations.get(selectedIndex).getId()
                        );


                //if both edit sections (address and patient == true)
                if(editionSuccess[0] && editionSuccess[1]) {
                    loadDataToTable();
                    editTabDisable(true);
                    tabPane.getSelectionModel().select(createDoctorTab);
                }


            });

            declineEditButton.setOnAction(e -> {
                editTabDisable(true);
                tabPane.getSelectionModel().select(createDoctorTab);
            });
        } else {
            DialogBox.warningBox("Information", "Please select doctor to edit in table");
        }
    }


    /**
     * Creating doctor from values in textfields and saving him to database.
     */
    @FXML
    private void createDoctorClicked() {
        Doctor doctorToAdd = new Doctor();
        Address addressToAdd = new Address();
        try {
            doctorToAdd.setFirstName(forenameFieldAdd.getTextValidated());
            doctorToAdd.setLastName(nameFieldAdd.getTextValidated());
            doctorToAdd.setPesel(peselFieldAdd.getTextValidated());
            doctorToAdd.setAddress(addressToAdd);
            doctorToAdd.setEmail(emailFieldAdd.getTextValidated());
            doctorToAdd.setPhoneNumber(phoneFieldAdd.getTextValidated());
            doctorToAdd.setSpecialization(specializations.get(specializationChoiceBox.getSelectionModel().getSelectedIndex()));

            doctorToAdd.setPassword(nameFieldAdd.getTextValidated());


            addressToAdd.setCity(cityFieldAdd.getTextValidated());
            addressToAdd.setZip(zipFieldAdd.getTextValidated());
            addressToAdd.setStreet(streetFieldAdd.getTextValidated());
            addressToAdd.setNumber(Integer.parseInt(numberFieldAdd.getTextValidated()));

            doctorAdministrationDTO.add(doctorToAdd);
            loadDataToTable();
            //clearAddDoctorFields();
        }
        catch (CustomControlsException | ArrayIndexOutOfBoundsException ex) {
            if(ex.getClass() == ArrayIndexOutOfBoundsException.class) {
                DialogBox.validationErrorBox("Wrong patient data!",
                        "Please choose specialization");
            } else {
                DialogBox.validationErrorBox("Wrong doctor data!", ex.getMessage());
            }
        }

    }

    /**
     * Action for invoke method responsible for clearing textfields using
     * for creating doctor.
     */
    @FXML
    private void clearDoctorClickedAdd() {
        clearAddDoctorFields();
    }


    /**
     * Switching scene back to admin default screen after login.
     *
     * @param event         using by pagination helper for get current scene.
     *                      It is necessary to switch from one scene to another.
     * @see helpers.ControllerHelpers
     * @throws IOException  throwing when fxml file wasn't found
     */
    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/AdminHome", event);
    }


    /**
     * Creating doctor from textfields used to change values.
     * @param doctor   old patient version (containing his id), which will be
     *                  override.
     * @return          doctor, which will be updated in database.
     */
    private Doctor createDoctorForEditFromTextfields(Doctor doctor) {
        List<Specialization> specializations = doctorAdministrationDTO.getAllSpecializations();

        try {
            doctor.setFirstName(forenameField.getTextValidated());
            doctor.setLastName(nameField.getTextValidated());
            doctor.setPesel(peselField.getTextValidated());
            doctor.setEmail(emailField.getTextValidated());
            doctor.setPhoneNumber(phoneField.getTextValidated());
            //doctor.setSpecializationId(Integer.parseInt(specializationField.getText()));

            int specIndex = specializationEditChoiceBox.getSelectionModel().getSelectedIndex();
            Specialization specSelected = specializations.get(specIndex);
            doctor.setSpecializationId(specSelected.getId());
            editionSuccess[0] = true;
        } catch (CustomControlsException | ArrayIndexOutOfBoundsException ex) {
            editionSuccess[0] = false;
            if(ex.getClass() == ArrayIndexOutOfBoundsException.class) {
                DialogBox.validationErrorBox("Wrong doctor data!",
                        "Please choose specialization");
            } else {
                DialogBox.validationErrorBox("Wrong doctor data!", ex.getMessage());
            }
        }
        return doctor;
    }

    /**
     * Creating address from textfields, which will be saved with an override
     * patient in database.
     * @param address   old address (containing unique id), which will be override.
     * @return          address, which will be updated in database.
     */
    private Address createAddressForEditFromTexfields(Address address) {
        Address addressToReturn = address;
        try {
            addressToReturn.setCity(cityField.getTextValidated());
            addressToReturn.setZip(zipField.getTextValidated());
            addressToReturn.setStreet(streetField.getTextValidated());
            addressToReturn.setNumber(Integer.parseInt(String.valueOf(
                    numberField.getTextValidated())));
            editionSuccess[1] = true;
        } catch (CustomControlsException ex) {
            DialogBox.validationErrorBox("Wrong doctor data", ex.getMessage());
            editionSuccess[1] = false;
        }

        return addressToReturn;
    }


    /**
     * Filling containing before-update doctor version textfields.
     *
     * @param doctor which data will be set to textfields.
     */
    private void fillEditDoctorFields(Doctor doctor) {
        peselField.setText(doctor.getPesel());
        forenameField.setText(doctor.getFirstName());
        nameField.setText(doctor.getLastName());
        peselField.setText(doctor.getPesel());
        emailField.setText(doctor.getEmail());
        phoneField.setText(doctor.getPhoneNumber());
        loadSpecializationsToChoiceBox();
        specializationEditChoiceBox.getSelectionModel().select(doctor.getSpecialization().getName());
    }

    /**
     * Filling containing before-update address version textfields.
     * @param address which data will be set to textfields.
     */
    private void fillEditDoctorAddressFields(Address address) {
        cityField.setText(address.getCity());
        zipField.setText(address.getZip());
        streetField.setText(address.getStreet());
        numberField.setText(String.valueOf(address.getNumber()));
    }

    private void loadSpecializationsToChoiceBox() {
        List<Specialization> specializations = doctorAdministrationDTO.getAllSpecializations();
        List<String> stringSpecializations = new ArrayList<>();
        for(Specialization spec : specializations) {
            stringSpecializations.add(spec.getName());
        }

        specializationEditChoiceBox.setItems(FXCollections.observableArrayList(stringSpecializations));
    }

    /**
     * Clearing all textfields responsible for store doctor to create data.
     */
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

        specializationChoiceBox.getSelectionModel().selectFirst();
    }





    //HELPER METHODS

    /**
     * Getting patient, which is currently selected in table.
     *
     * @return selected in table patient.
     */
    private Doctor getSelectedDoctorInTable() {
        return doctorsTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Setting edit tab in tabPane enabled or disabled.
     *
     * @param bool true if you want disable edit patient tab
     */
    private void editTabDisable(boolean bool) {
        editDoctorTab.setDisable(bool);
    }

    private List<String> specializationsToString() {
        List<String> listToReturn = new ArrayList<>();
        for (Specialization s : specializations) {
            listToReturn.add(s.getName());
        }
        return listToReturn;
    }


    //SPECIALIZATIONS

    /**
     * Creating specalization form value in textField and saving it
     * in database.
     */
    @FXML
    private void createSpecializationButtonClicked() {
        Specialization specializationToAdd = new Specialization();
        specializationToAdd.setName(createSpecializationField.getText());

        doctorAdministrationDTO.createSpecialization(specializationToAdd);
        DialogBox.informationBox("Success!", "Specialization created successfully.");
        setSpecializations();
    }

    /**
     * Removing selected in listView specialization from database.
     */
    @FXML
    private void removeSpecializationClicked() {
        Specialization selectedToRemoveSpec =
                removeSpecializationListView.getSelectionModel().getSelectedItem();
        int specId = selectedToRemoveSpec.getId();
        doctorAdministrationDTO.deleteSpecialization(specId);
        DialogBox.informationBox("Success!", "Specialization removed successfully.");
        setSpecializations();

    }







}
