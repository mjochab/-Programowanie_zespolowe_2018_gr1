package controllers;

import entities.Doctor;
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
import repositories.DoctorRepository;
import repositories.RepositoryInterface;

import java.io.IOException;
import java.util.function.Predicate;

public class AdminDoctorController implements ControllerPagination {

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
            specializationField,
            forenameFieldAdd,
            nameFieldAdd,
            peselFieldAdd,
            addressFieldAdd,
            specializationFieldAdd;

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
            addressColumn,
            specializationColumn;

    private RepositoryInterface doctorRepository;

    private FilteredList filteredList;

    ObservableList<Doctor> tableData;

    public AdminDoctorController() {
        this.doctorRepository = new DoctorRepository();
    }

    @FXML
    private void initialize() {

        //setup columns in the table
        idColumn.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("id"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("forename"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("pesel"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("address"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));

        //load data
        loadDataToTable();

        filteredList = new FilteredList(tableData, e->true);    //list using to filter data


        editTabDisable(true);
    }

    private void loadDataToTable() {

        //if (tableData != null)
        //    tableData.removeAll();

        tableData = FXCollections.observableArrayList(doctorRepository.getAll());
        doctorsTable.setItems(tableData);
        filteredList = new FilteredList(tableData, e->true);
        doctorsTable.refresh();

        String s = "aaa";

    }


    @FXML
    private void searchDoctorsByPeselAndName(KeyEvent event) {
        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate((Predicate<? super Doctor>) (Doctor doctor)->{
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                else if (doctor.getName().toLowerCase().contains(newValue.toLowerCase())) {
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

        if ( PopBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
                doctorToDelete.getForename(), doctorToDelete.getName()), "Are you sure?") ) {

            doctorRepository.remove(doctorToDelete);

            loadDataToTable();
            System.out.println("Ilosc pacjentow w repo: " + doctorRepository.getAll().size());
        }
    }

    @FXML
    private void editDoctorClicked(ActionEvent event) {
        editTabDisable(false);
        tabPane.getSelectionModel().select(editDoctorTab);

        Doctor doctorToEdit = getSelectedDoctorInTable();
        System.out.println("Doctor to edit id: "  + getSelectedDoctorInTable().getId());

        fillEditDoctorFields(doctorToEdit);

        saveEditButton.setOnAction(e -> {
            doctorRepository.update(createDoctorForEditFromTextfields(doctorToEdit.getId()));

            loadDataToTable();
            editTabDisable(true);
            tabPane.getSelectionModel().select(createDoctorTab);
        });

        declineEditButton.setOnAction(e -> {
            editTabDisable(true);
            tabPane.getSelectionModel().select(createDoctorTab);
        });

    }

    @FXML
    private void createDoctorClicked(ActionEvent event) {
        Doctor doctorToAdd = new Doctor(
                doctorRepository.getNewMaxId(),
                forenameFieldAdd.getText(),
                nameFieldAdd.getText(),
                nameFieldAdd.getText(), //name as password
                peselFieldAdd.getText(),
                addressFieldAdd.getText(),
                specializationFieldAdd.getText()
        );
        doctorRepository.add(doctorToAdd);

        loadDataToTable();
        clearAddDoctorFields();
    }

    @FXML
    private void clearDoctorClickedAdd(ActionEvent event) {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        addressFieldAdd.setText(null);
        specializationFieldAdd.setText(null);
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("AdminHome", event);
    }

    private Doctor createDoctorForEditFromTextfields(int id) {
        //(int id, String forename, String name, String password, String pesel, String address)
        Doctor doctorToReturn = new Doctor(
                id,
                forenameField.getText(),
                nameField.getText(),
                nameField.getText(),
                peselField.getText(),
                addressField.getText(),
                specializationField.getText()
        );
        return doctorToReturn;
    }


    private void fillEditDoctorFields(Doctor doctor) {
        peselField.setText(doctor.getPesel());
        forenameField.setText(doctor.getForename());
        nameField.setText(doctor.getName());
        addressField.setText(doctor.getAddress());
        specializationField.setText(doctor.getSpecialization());
    }

    private void clearAddDoctorFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        addressFieldAdd.setText(null);
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
