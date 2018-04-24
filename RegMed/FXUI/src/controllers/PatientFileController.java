package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class PatientFileController  implements Initializable {

    @FXML
    private TableView<PatientData> tableID ;
    @FXML
    private TableColumn<PatientData, Integer> id ;
    @FXML
    private TableColumn<PatientData, String> firstName;
    @FXML
    private TableColumn<PatientData, String> lastName;


    ObservableList<PatientData> data = FXCollections.observableArrayList(
        new PatientData(1,"Docent","Fiut"),
            new PatientData(2,"Krystyna","Ciapciak"),
            new PatientData(3,"Władysława","Chujdus")

    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<PatientData, Integer>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<PatientData, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<PatientData, String>("lastName"));
        tableID.setItems(data);
    }
}
