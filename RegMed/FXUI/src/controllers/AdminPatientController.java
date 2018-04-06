package controllers;

import entities.Patient;
import helpers.ControllerPagination;
import helpers.PopBox;
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
import repositories.PatientRepository;
import repositories.RepositoryInterface;

import java.io.IOException;


public class AdminPatientController implements ControllerPagination {

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
    private TableView<Patient> patientsTable;
    @FXML
    private TableColumn<Patient, Integer> idColumn;
    @FXML
    private TableColumn<Patient, String> forenameColumn,
            nameColumn,
            peselColumn,
            addressColumn;

    private RepositoryInterface<Patient> patientRepository;
    //private PatientRepository patientRepository;

    private FilteredList filteredList;

    ObservableList<Patient> tableData;

    public AdminPatientController() {
        this.patientRepository = new PatientRepository();
    }

    @FXML
    private void initialize() {

        //setup columns in the table
        idColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("forename"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("pesel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));

        //load data
        loadDataToTable();

        filteredList = new FilteredList(tableData, e->true);    //list using to filter data


        editTabDisable(true);
    }

    private void loadDataToTable() {

        //if (tableData != null)
        //    tableData.removeAll();

        tableData = FXCollections.observableArrayList(patientRepository.getAll());
        patientsTable.setItems(tableData);
        filteredList = new FilteredList(tableData, e->true);
        patientsTable.refresh();
    }


    @FXML
    private void searchPatientsByPeselAndName(KeyEvent event) {
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
        filteredList.setPredicate((java.util.function.Predicate<? super Patient>) (Patient patient)->{
            if (newValue.isEmpty() || newValue == null) {
                return true;
            }
            else if (patient.getName().contains(newValue)) {
                return true;
            }
            else if (patient.getPesel().contains(newValue)) {
                return true;
            }
            return false;
        });
        }));

        SortedList sort = new SortedList(filteredList);
        sort.comparatorProperty().bind(patientsTable.comparatorProperty());
        patientsTable.setItems(sort);
    }



    //--ACTION_METHODS--



    @FXML
    private void removePatientClicked(ActionEvent event) {
        Patient patientToDelete = getSelectedPatientInTable();

        if ( PopBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
                patientToDelete.getForename(), patientToDelete.getName()), "Are you sure?") ) {

            patientRepository.remove(patientToDelete);

            loadDataToTable();
            System.out.println("Ilosc pacjentow w repo: " + patientRepository.getAll().size());
        }
    }

    @FXML
    private void editPatientClicked(ActionEvent event) {
        editTabDisable(false);
        tabPane.getSelectionModel().select(editPatientTab);

        Patient patientToEdit = getSelectedPatientInTable();
        System.out.println("Patient to edit id: "  + getSelectedPatientInTable().getId());

        fillEditPatientFields(patientToEdit);

        saveEditButton.setOnAction(e -> {
            patientRepository.update(createPatientForEditFromTextfields(patientToEdit.getId()));

            loadDataToTable();
            editTabDisable(true);
            tabPane.getSelectionModel().select(createPatientTab);
        });

        declineEditButton.setOnAction(e -> {
            editTabDisable(true);
            tabPane.getSelectionModel().select(createPatientTab);
        });

    }

    @FXML
    private void createPatientClicked(ActionEvent event) {
        Patient patientToAdd = new Patient(
                patientRepository.getNewMaxId(),
                forenameFieldAdd.getText(),
                nameFieldAdd.getText(),
                nameFieldAdd.getText(), //name as password
                peselFieldAdd.getText(),
                addressFieldAdd.getText()
        );
        patientRepository.add(patientToAdd);

        loadDataToTable();
        clearAddPatientFields();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("AdminHome", event);
    }

    private Patient createPatientForEditFromTextfields(int id) {
        //(int id, String forename, String name, String password, String pesel, String address)
        Patient patientToReturn = new Patient(
                id,
                forenameField.getText(),
                nameField.getText(),
                nameField.getText(),
                peselField.getText(),
                addressField.getText()
        );
        return patientToReturn;
    }


    private void fillEditPatientFields(Patient patient) {
        peselField.setText(patient.getPesel());
        forenameField.setText(patient.getForename());
        nameField.setText(patient.getName());
        addressField.setText(patient.getAddress());
    }

    private void clearAddPatientFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        addressFieldAdd.setText(null);
    }





    //HELPER METHODS

    private Patient getSelectedPatientInTable() {
        return patientsTable.getSelectionModel().getSelectedItem();
    }

    private void editTabDisable(boolean bool) {
        editPatientTab.setDisable(bool);
    }


}
