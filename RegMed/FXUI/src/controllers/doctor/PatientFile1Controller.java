package controllers.doctor;

import database.MyBatisDbConnection;
import dto.PatientDataDTO;
import dto.PatientListDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.PatientData;

import java.net.URL;

import java.util.ResourceBundle;

import static controllers.homescreen.HomescreenController.loggedUser;


public class PatientFile1Controller implements Initializable {

    @FXML
    private TableView<PatientData> tableID;
    @FXML
    private TableColumn<PatientData, Integer> patientId;
    @FXML
    private TableColumn<PatientData, String> firstName;
    @FXML
    private TableColumn<PatientData, String> lastName;


    private ObservableList tableData;
    private PatientDataDTO patientDataDTO;
    private int selectedPatientId;
    protected static Integer getSelectedPatientId;


    public PatientFile1Controller() {
        this.patientDataDTO = new PatientDataDTO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PatientListDTO patientList = new PatientListDTO();
        patientId.setCellValueFactory(new PropertyValueFactory<PatientData, Integer>("patientId"));
        firstName.setCellValueFactory(new PropertyValueFactory<PatientData, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<PatientData, String>("lastName"));
        tableData = FXCollections.observableArrayList(patientList.getPatientList(loggedUser));
        tableID.setItems(tableData);
        tableOnChange();
    }

    /**
     * Listener special for listen which patient from table was selected
     */
    public void tableOnChange() {
        tableID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedPatientId = tableID.getSelectionModel().getSelectedItem().patientId;
                getSelectedPatientId = selectedPatientId;
            }
        });
    }
}
