package controllers;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class DoctorMainController implements ControllerPagination {

    @FXML
    Label
            nameLabel,
            dateLabel,
            hourLabel,
            minuteLabel;

    @FXML
    Button
            admissionModuleButton,
            editButton,
            reportButton,
            logoutButton;

    @FXML
    BorderPane
            borderPane;

    @FXML public void initialize(){

    }

    @FXML void switchAdmissionModule(ActionEvent event) throws IOException{
        borderPane.setCenter(helpers.SwitchAnchor("DoctorEdit", event));
    }

    @FXML void switchEdit(ActionEvent event) throws IOException{
        borderPane.setCenter(helpers.SwitchAnchor("DoctorEdit", event));
    }

    @FXML void switchReport(ActionEvent event) throws IOException{
        borderPane.setCenter(helpers.SwitchAnchor("DoctorReport", event));
    }

    @FXML void logOut(ActionEvent event) throws IOException{
        borderPane.setCenter(helpers.SwitchAnchor("DoctorReport", event));
    }
}
