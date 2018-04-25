package controllers;

import helpers.ControllerPagination;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import javafx.util.*;



public class DoctorMainController implements ControllerPagination {

    @FXML
    Label
            nameLabel,
            dateLabel,
            timeLabel,
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
            borderPane,
            leftBorderPane,
            file_2;

    @FXML
    AnchorPane anchorPane,
                anchorEdit,
                anchorReport,
                anchorAdmissionTable;

    @FXML public void initialize(){
        //helpers.getCurrentDateTime(dateLabel, timeLabel);
    }

    @FXML void switchAdmissionModule(ActionEvent event) throws IOException{
        //borderPane.setCenter(helpers.SwitchAnchor("DoctorEdit", event));
        setAllNotVisible();
        file_2.setVisible(true);
    }

    @FXML void switchEdit(ActionEvent event) throws IOException{
        setAllNotVisible();
        anchorEdit.setVisible(true);
    }

    @FXML void switchReport(ActionEvent event) throws IOException{
        setAllNotVisible();
        anchorReport.setVisible(true);
        //borderPane.setCenter(helpers.SwitchAnchor("DoctorReport", event));
    }

    @FXML void logOut(ActionEvent event) throws IOException{
        helpers.SwitchScene("Homescreen", event);
    }

    @FXML void AdmissonModuleClicked(ActionEvent event) throws IOException{
        //leftBorderPane.setBottom(helpers.SwitchAnchor("PatientFile_1",event));
        //borderPane.setCenter(helpers.SwitchAnchor("PatientFile_2",event));
        setAllNotVisible();
        anchorAdmissionTable.setVisible(true);
        file_2.setVisible(true);
    }

    private void setAllNotVisible() {
        anchorReport.setVisible(false);
        anchorEdit.setVisible(false);
        file_2.setVisible(false);
        anchorAdmissionTable.setVisible(false);
    }
}
