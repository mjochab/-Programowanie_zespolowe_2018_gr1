package controllers.admin;

import pojo.Administrator;
import helpers.ControllerPagination;
import helpers.DialogBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import dto.AdminAdministrationDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminAdministrationController implements Initializable, ControllerPagination {

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
            phoneNumberField,

            forenameFieldAdd,
            nameFieldAdd,
            peselFieldAdd,
            emailFieldAdd,
            phoneNumberFieldAdd;
    @FXML
    private Label lLogged;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editAdministratorTab,
            createAdministratorTab;

    @FXML
    private TableView<Administrator> administratorsTable;
    @FXML
    private TableColumn<Administrator, Integer> idColumn;
    @FXML
    private TableColumn<Administrator, String> forenameColumn,
            nameColumn,
            peselColumn,
            emailColumn,
            phoneNumberColumn;


    private AdminAdministrationDTO adminAdministrationDTO;
    private FilteredList filteredList;
    private ObservableList tableData;
    public static Administrator administrator;

    public AdminAdministrationController() {
        this.adminAdministrationDTO = new AdminAdministrationDTO();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupColumnsInTheTable();
        loadDataToTable();
        filteredList = new FilteredList(tableData, e->true);
        editTabDisable(true);
        lLogged.setText(administrator.getFirstName() + " " + administrator.getLastName());
    }

    private void setupColumnsInTheTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Administrator, Integer>("adminId"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("firstName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("lastName"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("pesel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("phoneNumber"));
    }

    private void loadDataToTable() {
        tableData = FXCollections.observableArrayList(adminAdministrationDTO.getAll());
        administratorsTable.setItems(tableData);
        filteredList = new FilteredList(tableData, e->true);
        administratorsTable.refresh();
    }




    //--ACTION_METHODS--

    @FXML
    private void searchAdministratorsByPeselAndName(KeyEvent event) {
        searchField.textProperty().addListener(((observable, oldValue, newValue) ->{
            filteredList.setPredicate((java.util.function.Predicate<? super Administrator>) (Administrator administrator) ->{
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (administrator.getFirstName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (administrator.getPesel().contains(newValue)) {
                    return true;
                }
                return false;
            });
        }));

        //Seting sorted items in administrators table
        SortedList sort = new SortedList(filteredList);
        sort.comparatorProperty().bind(administratorsTable.comparatorProperty());
        administratorsTable.setItems(sort);
    }


    @FXML
    private void removeAdministratorClicked(ActionEvent event) {
        Administrator administratorToDelete = getSelectedAdministratorInTable();

        if (getSelectedAdministratorInTable() != null) {
            if (DialogBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
                    administratorToDelete.getFirstName(), administratorToDelete.getLastName()), "Are you sure?")) {

                adminAdministrationDTO.delete(administratorToDelete.getAdminId());

                loadDataToTable();
            }
        } else {
            DialogBox.warningBox("Information", "Please select patient to remove in table");
        }
    }


    @FXML
    private void editAdministratorClicked(ActionEvent event) {
        if (getSelectedAdministratorInTable() != null) {

            editTabDisable(false);
            tabPane.getSelectionModel().select(editAdministratorTab);

            Administrator administratorToEdit = getSelectedAdministratorInTable();
            fillEditAdministratorFields(administratorToEdit);

            saveEditButton.setOnAction(e -> {
                adminAdministrationDTO.update(createAdministratorForEditFromTextfields(administratorToEdit));
                loadDataToTable();
                editTabDisable(true);
                tabPane.getSelectionModel().select(createAdministratorTab);
            });

            declineEditButton.setOnAction(e -> {
                editTabDisable(true);
                tabPane.getSelectionModel().select(createAdministratorTab);
            });
        } else {
            DialogBox.warningBox("Information", "Please select administrator to edit in table");
        }
    }

    @FXML
    private void createAdministratorClicked(ActionEvent event) {
        Administrator administratorToAdd = new Administrator();
        administratorToAdd.setFirstName(forenameFieldAdd.getText());
        administratorToAdd.setLastName(nameFieldAdd.getText());
        administratorToAdd.setPesel(peselFieldAdd.getText());
        administratorToAdd.setEmail(emailFieldAdd.getText());
        administratorToAdd.setPhoneNumber(phoneNumberFieldAdd.getText());
        administratorToAdd.setPassword(nameFieldAdd.getText());

        adminAdministrationDTO.add(administratorToAdd);

        loadDataToTable();
        clearAddAdministratorFields();
    }

    @FXML
    private void clearAdministratorClickedAdd(ActionEvent event) {
        clearAddAdministratorFields();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/AdminHome", event);
    }




    private void fillEditAdministratorFields(Administrator administrator) {
        peselField.setText(administrator.getPesel());
        forenameField.setText(administrator.getFirstName());
        nameField.setText(administrator.getLastName());
        emailField.setText(administrator.getEmail());
        phoneNumberField.setText(administrator.getPhoneNumber());
    }

    private Administrator createAdministratorForEditFromTextfields(Administrator administrator) {
        Administrator administratorToReturn = administrator;
        administratorToReturn.setFirstName(forenameField.getText());
        administratorToReturn.setLastName(nameField.getText());
        administratorToReturn.setPesel(peselField.getText());
        administratorToReturn.setEmail(emailField.getText());
        administratorToReturn.setPhoneNumber(phoneNumberField.getText());

        return administratorToReturn;
    }

    private void clearAddAdministratorFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        emailFieldAdd.setText(null);
        phoneNumberFieldAdd.setText(null);
    }





    //HELPER METHODS

    private Administrator getSelectedAdministratorInTable() {
        return administratorsTable.getSelectionModel().getSelectedItem();
    }

    private void editTabDisable(boolean bool) {
        editAdministratorTab.setDisable(bool);
    }



}
