package controllers.admin;

import entities.Doctor;
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
import repositories.DoctorRepository;
import repositories.RepositoryInterface;

import java.io.IOException;
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

    @FXML
    private Tab editWorkingHoursTab;

    private RepositoryInterface doctorRepository;

    private FilteredList filteredList;

    ObservableList<Doctor> tableData;

    public DoctorAdministrationController() {
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

        if(getSelectedDoctorInTable() != null) {
            if ( DialogBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
                    doctorToDelete.getForename(), doctorToDelete.getName()), "Are you sure?") ) {

                doctorRepository.remove(doctorToDelete);

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

            saveEditButton.setOnAction(e -> {
                doctorRepository.update(createDoctorForEditFromTextfields(doctorToEdit));

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
        clearAddDoctorFields();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/AdminHome", event);
    }

    private Doctor createDoctorForEditFromTextfields(Doctor doctor) {
        //(int id, String forename, String name, String password, String pesel, String address)
        Doctor doctorToReturn = new Doctor(
                doctor.getId(),
                forenameField.getText(),
                nameField.getText(),
                doctor.getPassword(),
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
