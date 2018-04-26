package controllers.admin;

import entities.Administrator;
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
import repositories.AdministratorRepository;
import repositories.RepositoryInterface;

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
            addressField,
            forenameFieldAdd,
            nameFieldAdd,
            peselFieldAdd,
            addressFieldAdd;

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
            peselColumn;


    private RepositoryInterface administratorRepository;
    private FilteredList filteredList;
    private ObservableList tableData;

    public AdminAdministrationController() {
        this.administratorRepository = new AdministratorRepository();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupColumnsInTheTable();
        loadDataToTable();
        filteredList = new FilteredList(tableData, e->true);
        editTabDisable(true);
    }

    private void setupColumnsInTheTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Administrator, Integer>("id"));
        forenameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("forename"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("name"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<Administrator, String>("pesel"));
    }

    private void loadDataToTable() {
        tableData = FXCollections.observableArrayList(administratorRepository.getAll());
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
                } else if (administrator.getName().toLowerCase().contains(newValue.toLowerCase())) {
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
                    administratorToDelete.getForename(), administratorToDelete.getName()), "Are you sure?")) {

                administratorRepository.remove(administratorToDelete);

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
                administratorRepository.update(createAdministratorForEditFromTextfields(administratorToEdit));
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
        Administrator administratorToAdd = new Administrator(
                administratorRepository.getNewMaxId(),
                forenameFieldAdd.getText(),
                nameFieldAdd.getText(),
                nameFieldAdd.getText(), //name as password
                peselFieldAdd.getText()
        );
        administratorRepository.add(administratorToAdd);

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
        forenameField.setText(administrator.getForename());
        nameField.setText(administrator.getName());
    }

    private Administrator createAdministratorForEditFromTextfields(Administrator administrator) {
        Administrator administratorToReturn = new Administrator(
                administrator.getId(),
                forenameField.getText(),
                nameField.getText(),
                administrator.getPassword(),
                peselField.getText()
        );
        return administratorToReturn;
    }

    private void clearAddAdministratorFields() {
        forenameFieldAdd.setText(null);
        nameFieldAdd.setText(null);
        peselFieldAdd.setText(null);
    }


    //HELPER METHODS

    private Administrator getSelectedAdministratorInTable() {
        return administratorsTable.getSelectionModel().getSelectedItem();
    }

    private void editTabDisable(boolean bool) {
        editAdministratorTab.setDisable(bool);
    }



}
