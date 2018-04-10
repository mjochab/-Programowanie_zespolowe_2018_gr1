package controllers;

import helpers.ControllerPagination;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import javafx.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DoctorMainController implements ControllerPagination {

    @FXML
    Label
            nameLabel,
            dateLabel,
            timeLabel;

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
        helpers.getCurrentDateTime(dateLabel, timeLabel);
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
        helpers.SwitchScene("AdminHome", event);
    }
}
