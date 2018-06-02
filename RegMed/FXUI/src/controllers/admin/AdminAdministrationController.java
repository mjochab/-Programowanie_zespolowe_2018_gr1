package controllers.admin;

import customControls.NameTextField;
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
import dto.AdminAdministrationDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Administrators administration.
 * Containing crud operations and governing fxml form behavior (inserting values
 * into textfields etc).
 *
 * @see     helpers.ControllerPagination is using to hold helpers, mostly for
 *          changing pages.
 * @author  Szymon P
 */
public class AdminAdministrationController implements Initializable, ControllerPagination {

    @FXML
    private Button
            saveEditButton,
            declineEditButton;

    //@FXML
   // private NameTextField forenameFieldAdd;

    @FXML
    private TextField
            idField,
            searchField,
            peselField,
            forenameField,
            nameField,
            emailField,
            phoneNumberField,


            peselFieldAdd,
            emailFieldAdd,
            phoneNumberFieldAdd;

    @FXML
    private NameTextField forenameFieldAdd,
            nameFieldAdd;


    @FXML
    private TabPane
            tabPane;

    @FXML
    private Tab
            editAdministratorTab,
            createAdministratorTab;

    @FXML
    private TableView<Administrator>
            administratorsTable;
    @FXML
    private TableColumn<Administrator, Integer>
            idColumn;
    @FXML
    private TableColumn<Administrator, String>
            forenameColumn,
            nameColumn,
            peselColumn,
            emailColumn,
            phoneNumberColumn;


    private AdminAdministrationDTO adminAdministrationDTO;
    private FilteredList filteredList;
    private ObservableList tableData;

    public AdminAdministrationController() {
        this.adminAdministrationDTO = new AdminAdministrationDTO();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupColumnsInTheTable();
        loadDataToTable();
        filteredList = new FilteredList(tableData, e->true);
        editTabDisable(true);
    }

    /**
     * Setting factory relation between values in table and pojo class(es).
     */
    private void setupColumnsInTheTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Administrator, Integer>("id"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("firstName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("lastName"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("pesel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("phoneNumber"));
    }

    /**
     * Loading/refreshing data in table. Also applying filteredList on table
     * from method searchAdministratorsByPeselAndName.
     */
    private void loadDataToTable() {
        tableData = FXCollections.observableArrayList(adminAdministrationDTO.getAll());
        administratorsTable.setItems(tableData);
        filteredList = new FilteredList(tableData, e->true);
        administratorsTable.refresh();
    }




    //--ACTION_METHODS--

    /**
     * Filtering administrators in table using pesel or name.
     */
    @FXML
    private void filterAdministratorsByPeselAndName() {
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


    /**
     * Removing selected in table administrator from database.
     */
    @FXML
    private void removeAdministratorClicked() {
        Administrator administratorToDelete = getSelectedAdministratorInTable();

        if (getSelectedAdministratorInTable() != null) {
            if (DialogBox.choiceBox("Remove confirmation", String.format("%s %s will be removed.",
                    administratorToDelete.getFirstName(), administratorToDelete.getLastName()), "Are you sure?")) {

                adminAdministrationDTO.delete(administratorToDelete.getId());

                loadDataToTable();
            }
        } else {
            DialogBox.warningBox("Information", "Please select patient to remove in table");
        }
    }


    /**
     * Editing administrator in database.
     * Downloading selected to edit administrator from database and setting
     * him data specified in administrator object created from textfields.
     */
    @FXML
    private void editAdministratorClicked() {
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

    /**
     * Creating administrator from values in textfields and saving him to database.
     */
    @FXML
    private void createAdministratorClicked() {
        Administrator administratorToAdd = new Administrator();
        administratorToAdd.setFirstName(forenameFieldAdd.getTextFormatted());
        administratorToAdd.setLastName(nameFieldAdd.getTextFormatted());
        administratorToAdd.setPesel(peselFieldAdd.getText());
        administratorToAdd.setEmail(emailFieldAdd.getText());
        administratorToAdd.setPhoneNumber(phoneNumberFieldAdd.getText());
        administratorToAdd.setPassword(nameFieldAdd.getText());


            adminAdministrationDTO.add(administratorToAdd);

            loadDataToTable();
            clearAddAdministratorFields();


    }





    /**
     * Action for invoke method responsible for clearing textfields using
     * for creating administrator.
     */
    @FXML
    private void clearAdministratorClickedAdd() {
        clearAddAdministratorFields();
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
     * Using for fill edit administrator textfields.
     *
     * @param administrator using for store values, which will be
     *                      setted up in textfields.
     */
    private void fillEditAdministratorFields(Administrator administrator) {
        peselField.setText(administrator.getPesel());
        forenameField.setText(administrator.getFirstName());
        nameField.setText(administrator.getLastName());
        emailField.setText(administrator.getEmail());
        phoneNumberField.setText(administrator.getPhoneNumber());
    }

    /**
     * Creating edited administrator, which later will override his old version
     * in database.
     *
     * @param administrator will be used for save in database (his id especially)
     * @return              administrator which will override current admin with
     *                      same id.
     */
    private Administrator createAdministratorForEditFromTextfields(Administrator administrator) {
        Administrator administratorToReturn = administrator;
        administratorToReturn.setFirstName(forenameField.getText());
        administratorToReturn.setLastName(nameField.getText());
        administratorToReturn.setPesel(peselField.getText());
        administratorToReturn.setEmail(emailField.getText());
        administratorToReturn.setPhoneNumber(phoneNumberField.getText());

        return administratorToReturn;
    }

    /**
     * Clearing all textfields using for creating administrator.
     */
    private void clearAddAdministratorFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
        emailFieldAdd.setText(null);
        phoneNumberFieldAdd.setText(null);
    }


    //HELPER METHODS

    /**
     * Returning administrator currently selected in table.
     *
     * @return already selected row in table.
     */
    private Administrator getSelectedAdministratorInTable() {
        return administratorsTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Setting edit tab in tabPane enabled or disabled.
     *
     * @param bool true if you want disable edit patient tab
     */
    private void editTabDisable(boolean bool) {
        editAdministratorTab.setDisable(bool);
    }



}
