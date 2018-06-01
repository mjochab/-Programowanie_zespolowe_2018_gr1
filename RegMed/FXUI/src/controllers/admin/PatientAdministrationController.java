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
import javafx.scene.layout.AnchorPane;
import models.VisitAdministrationModel;
import pojo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Patients administration.
 * Containing crud operations and governing fxml form behavior (inserting values
 * into textfields etc).
 *
 * @see     helpers.ControllerPagination is using to hold helpers, mostly for
 *          changing pages.
 * @author  Szymon P
 */
public class PatientAdministrationController implements ControllerPagination {

    @FXML
    private Button saveEditButton,
            declineEditButton,
            saveVisitChangesButton;

    @FXML
    private TextField searchField,
            peselField,
            forenameField,
            nameField,
            emailField,
            phoneField,
            cityField,
            zipField,
            streetField,
            numberField,

            forenameFieldAdd,
            nameFieldAdd,
            peselFieldAdd,
            emailFieldAdd,
            phoneFieldAdd,
            cityFieldAdd,
            zipFieldAdd,
            streetFieldAdd,
            numberFieldAdd;


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editPatientTab,
            createPatientTab,
            manageVisitsTab;

    @FXML
    private TableView<Patient> patientsTable;
    @FXML
    private TableColumn<Patient, Integer> idColumn;
    @FXML
    private TableColumn<Patient, String> forenameColumn,
            nameColumn,
            peselColumn,
            addressColumn,
            emailColumn, firstContactDoctorIdColumn
            ;

//    @FXML
//    private TableColumn<Doctor, String> ;

    @FXML
    private ChoiceBox<String> firstcontactDoctorChoiceBoxAdd,
            firstcontactDoctorChoiceBox;

    @FXML
    private TableView<VisitAdministrationModel> visitTableView;

    @FXML
    private TableColumn<VisitAdministrationModel, Integer> visitIdColumn;

    @FXML
    private TableColumn<VisitAdministrationModel, String> visitDoctorColumn,
        visitSpecializationColumn,
        visitHourColumn,
        visitDateColumn;

    @FXML
    private AnchorPane editVisitAnchorPane;


    @FXML
    private ChoiceBox<LocalTime> visitHourChoiceBox;

    @FXML
    private ChoiceBox<LocalDate> visitDateChoiceBox;


    private PatientAdministrationDTO patientAdministrationDTO;

    private FilteredList filteredList;
    ObservableList<Patient> tableData;
    private List<Doctor> firstContactDoctors;


    public PatientAdministrationController() {
        patientAdministrationDTO = new PatientAdministrationDTO();
        firstContactDoctors = patientAdministrationDTO.getAllFirstcontactDoctors();
    }


    @FXML
    private void initialize() {
        setupColumnsInTheTable();
        loadDataToTable();

        filteredList = new FilteredList(tableData, e->true);    //list using to filter data
        editTabDisable(true);

        firstcontactDoctorChoiceBoxAdd.setItems(FXCollections.observableArrayList(parseFirstcontactDoctorsToString()));
        firstcontactDoctorChoiceBox.setItems(FXCollections.observableArrayList(parseFirstcontactDoctorsToString()));

        setupColumnsInTheVisitsTable();

    }

    /**
     * Setting factory relation between values in table and pojo class(es).
     */
    private void setupColumnsInTheTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("pesel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
        firstContactDoctorIdColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstContactDoctor"));
    }


    private void setupColumnsInTheVisitsTable() {
        visitIdColumn.setCellValueFactory(new PropertyValueFactory<VisitAdministrationModel, Integer>("id"));
        visitDoctorColumn.setCellValueFactory(new PropertyValueFactory<VisitAdministrationModel, String>("doctor"));
        visitSpecializationColumn.setCellValueFactory(new PropertyValueFactory<VisitAdministrationModel, String>("specialization"));
        visitHourColumn.setCellValueFactory(new PropertyValueFactory<VisitAdministrationModel, String>("hour"));
        visitDateColumn.setCellValueFactory(new PropertyValueFactory<VisitAdministrationModel, String>("date"));
    }


    /**
     * Loading/refreshing data in table. Also applying filteredList on table
     * from method searchPatientsByPeselAndName.
     */
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


    /**
     * Filtering patients in table using pasel number or name.
     */
    @FXML
    private void filterPatientsByPeselAndName() {
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

    /**
     * Removing selected in table patient from database.
     */
    @FXML
    private void removePatientClicked() {
        Patient patientToDelete = getSelectedPatientInTable();

        if (getSelectedPatientInTable() != null) {
            if ( DialogBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
                    patientToDelete.getFirstName(), patientToDelete.getLastName()), "Are you sure?") ) {

                patientAdministrationDTO.delete(patientToDelete.getId());
                loadDataToTable();
            }
        } else {
            DialogBox.warningBox("Information", "Please select patient to remove in table");
        }
    }

    /**
     * Editing selected patient and saving into database.
     * Downloading selected in table patient from database and setting to him
     * values specified in textfields, which are getting from methods depending
     * for getting data and parse it to Patient object.
     * If any value is not selected in table, showing warning.
     */
    @FXML
    private void editPatientClicked() {
        if (getSelectedPatientInTable() != null) {

            editTabDisable(false);
            tabPane.getSelectionModel().select(editPatientTab);

            Patient patientToEdit = getSelectedPatientInTable();
            fillEditPatientFields(patientToEdit);
            fillEditPatientAddressFields(patientToEdit.getAddress());

            saveEditButton.setOnAction(e -> {
                patientAdministrationDTO.update(createPatientForEditFromTextfields(patientToEdit));
                patientAdministrationDTO.updateAddress(createAddressForEditFromTexfields(patientToEdit.getAddress()));
                patientAdministrationDTO.updateFirstcontactDoctorId(patientToEdit, firstContactDoctors
                        .get(firstcontactDoctorChoiceBox.getSelectionModel().getSelectedIndex()).getId());

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

    /**
     * Creating patient from values in textfields, and saving him to database.
     */
    @FXML
    private void createPatientClicked() {
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
        patientToAdd.setFirstContactDoctor(firstContactDoctors.get(firstcontactDoctorChoiceBoxAdd.getSelectionModel().getSelectedIndex()));
        patientToAdd.setPassword(nameFieldAdd.getText());

        patientAdministrationDTO.add(patientToAdd);

        loadDataToTable();
        clearAddPatientFields();
    }

    /**
     * Action for invoke method responsible for clearing textfields using in
     * creating patient process.
     */
    @FXML
    private void clearPatientClickedAdd() {
        clearAddPatientFields();
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



    @FXML
    private void manageVisitsTabClicked() {
        loadDataToVisitTable();
    }

    private void loadDataToVisitTable() {
        Patient selectedPatient;
        List<SingleVisit> visits = new ArrayList<>();

        try {
            selectedPatient = getSelectedPatientInTable();
            visits = patientAdministrationDTO.getVisitsForPatient(selectedPatient.getId());
        } catch (NullPointerException ex) {
            DialogBox.warningBox("Warning!", "Please first select patient in table.");
            tabPane.getSelectionModel().selectFirst();
        }

        List<VisitAdministrationModel> administrationModels = new ArrayList<>();
        for(SingleVisit visit : visits) {
            VisitAdministrationModel model = new VisitAdministrationModel(visit.getId(),
                    visit.getAdmissionDay2().getDoctor().toString(),
                    visit.getAdmissionDay2().getDoctor().getSpecialization().toString(),
                    visit.getVisitHour(),
                    visit.getAdmissionDay2().getDate());
            administrationModels.add(model);
        }

        visitTableView.setItems(FXCollections.observableArrayList(administrationModels));
        visitTableView.refresh();
    }

    @FXML
    public void removeVisitButtonClicked() {
        VisitAdministrationModel visitModelSelected = getSelectedVisit();

        if (DialogBox.choiceBox("Information", "Visit deletion",
                String.format("Are you sure you want remove: \n%d, %s, %s, %s, %s ?",
                        visitModelSelected.getId(),
                        visitModelSelected.getDoctor(),
                        visitModelSelected.getSpecialization(),
                        visitModelSelected.getHour(),
                        visitModelSelected.getDate()
                )
        )) {
            patientAdministrationDTO.deleteVisit(visitModelSelected.getId());
            loadDataToVisitTable();
        }
    }

    @FXML
    public void removeAllVisitsButtonClicked() {
        Patient patient = getSelectedPatientInTable();

        if (DialogBox.choiceBox("Information", "All patient visits deletion",
            String.format("Are you sure you want remove all visits assingned to patient %s %s (id:%d)?",
                    getSelectedPatientInTable().getFirstName(),
                    getSelectedPatientInTable().getLastName(),
                    getSelectedPatientInTable().getId()
            )
        )) {
            patientAdministrationDTO.deleteAllVisits(getSelectedPatientInTable().getId());
            loadDataToVisitTable();
        }
    }

    @FXML
    public void addVisitButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("views/patient/Registration", event);
        //TODO: later copy fxml from patientModule when will be fin, and little edit
    }

    @FXML
    public void editVisitButtonClicked() {
        if (visitDateChoiceBox.getItems().size() > 0) {
            visitDateChoiceBox.getItems().clear();
        }
        if (visitHourChoiceBox.getItems().size() > 0) {
            visitHourChoiceBox.getItems().clear();
        }


        VisitAdministrationModel visitModel = getSelectedVisit();
        SingleVisit singleVisit = patientAdministrationDTO.getVisit(visitModel.getId());
        editVisitAnchorPane.setVisible(true);

        Doctor doctor = singleVisit.getAdmissionDay2().getDoctor();

        List<AdmissionDay2> admissionDaysForSelectedDoctor =
                new ArrayList<>(patientAdministrationDTO.getAdmissionDaysAfterToday(doctor.getId()));

        List<LocalDate> admissionDates = new ArrayList<>();
        for (AdmissionDay2 admDay : admissionDaysForSelectedDoctor) {
            admissionDates.add(admDay.getDate());
        }




        visitDateChoiceBox.setItems(FXCollections.observableArrayList(admissionDates));
        visitDateChoiceBox.getSelectionModel().select(visitModel.getDate());

        try {
            loadHoursForVisit(admissionDaysForSelectedDoctor, visitModel.getId());
            visitHourChoiceBox.getSelectionModel().select(visitModel.getHour());
            visitDateChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                loadHoursForVisit(admissionDaysForSelectedDoctor, visitModel.getId());
            });
        } catch (ArrayIndexOutOfBoundsException ex) {
            DialogBox.warningBox("Error!", "This visit has already taken place . You can't edit it");
        }

        saveVisitChangesButton.setOnAction(e -> {
            LocalTime newTime = visitHourChoiceBox.getSelectionModel().getSelectedItem();
            int id = visitDateChoiceBox.getSelectionModel().getSelectedIndex();
            int newAdmissionDayId = admissionDaysForSelectedDoctor.get(id).getId();
            patientAdministrationDTO.updateVisitDateAndHour(visitModel.getId(), newTime, newAdmissionDayId);
            System.out.println(String.format("new hour: %s; new id: %d", id, newAdmissionDayId));
        });

    }

    private void loadHoursForVisit(List<AdmissionDay2> admissionDaysForSelectedDoctor, int currentVisitId) {
        int selectedAdmissionDayIndex = visitDateChoiceBox.getSelectionModel().getSelectedIndex();
        AdmissionDay2 selectedAdmissionDay = admissionDaysForSelectedDoctor.get(selectedAdmissionDayIndex);
        List<SingleVisit> freeVisits = patientAdministrationDTO.getFreeVisits(selectedAdmissionDay);
        List<LocalTime> freeVisitsHours = new ArrayList<>();
        for(SingleVisit visit : freeVisits) {
            freeVisitsHours.add(visit.getVisitHour());
        }
        freeVisitsHours.add(patientAdministrationDTO.getVisit(currentVisitId).getVisitHour());
        visitHourChoiceBox.setItems(FXCollections.observableArrayList(freeVisitsHours));
    }

    @FXML
    private void declineChangesButtonClicked() {
        editVisitAnchorPane.setVisible(false);

    }






    /**
     * Creating patient from textfields used to change values.
     * @param patient   old patient version (containing his id), which will be
     *                  override.
     * @return          patient, which will be updated in database.
     */
    private Patient createPatientForEditFromTextfields(Patient patient) {
        Patient patientToReturn = patient;

        patientToReturn.setFirstName(forenameField.getText());
        patientToReturn.setLastName(nameField.getText());
        patientToReturn.setPesel(peselField.getText());
        patientToReturn.setEmail(emailField.getText());
        patientToReturn.setPhoneNumber(phoneField.getText());
        patientToReturn.setFirstContactDoctor(firstContactDoctors.get(firstcontactDoctorChoiceBox.getSelectionModel().getSelectedIndex()));
        return patientToReturn;
    }

    /**
     * Creating address from textfields, which will be saved with an override
     * patient in database.
     * @param address   old address (containing unique id), which will be override.
     * @return          address, which will be updated in database.
     */
    private Address createAddressForEditFromTexfields(Address address) {
        Address addressToReturn = address;

        addressToReturn.setCity(cityField.getText());
        addressToReturn.setZip(zipField.getText());
        addressToReturn.setStreet(streetField.getText());
        addressToReturn.setNumber(Integer.parseInt(String.valueOf(numberField.getText())));

        return addressToReturn;
    }


    /**
     * Filling containing before-update patient version textfields.
     *
     * @param patient which data will be set to textfields.
     */
    private void fillEditPatientFields(Patient patient) {
        forenameField.setText(patient.getFirstName());
        nameField.setText(patient.getLastName());
        peselField.setText(patient.getPesel());
        emailField.setText(patient.getEmail());
        phoneField.setText(patient.getPhoneNumber());

    }

    /**
     * Filling containing before-update address version textfields.
     * @param address which data will be set to textfields.
     */
    private void fillEditPatientAddressFields(Address address) {
        cityField.setText(address.getCity());
        zipField.setText(address.getZip());
        streetField.setText(address.getStreet());
        numberField.setText(String.valueOf(address.getNumber()));
    }

    /**
     * Clearing all textfields responsible for store patient to create data.
     */
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

        firstcontactDoctorChoiceBoxAdd.getSelectionModel().selectFirst();
    }





    //HELPER METHODS

    /**
     * Getting patient, which is currently selected in table.
     *
     * @return selected in table patient.
     */
    private Patient getSelectedPatientInTable() {
        return patientsTable.getSelectionModel().getSelectedItem();
    }

    private VisitAdministrationModel getSelectedVisit() {
        return visitTableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Setting edit tab in tabPane enabled or disabled.
     *
     * @param bool true if you want disable edit patient tab
     */
    private void editTabDisable(boolean bool) {
        editPatientTab.setDisable(bool);
    }

    private List<String> parseFirstcontactDoctorsToString() {
        List<String> result = new ArrayList<>();
        for (Doctor doctor : firstContactDoctors) {
            result.add(String.format("%s %s", doctor.getFirstName(),
                    doctor.getLastName()));
        }
        return result;
    }


}
