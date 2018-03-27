package controllers;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class DoctorMainController implements ControllerPagination {

    @FXML Label nameLabel;

    @FXML Label dateLabel;

    @FXML Label hourLabel;

    @FXML Label minuteLabel;

    @FXML Button admissionModuleButton;

    @FXML Button editButton;

    @FXML Button reportButton;

    @FXML Button logoutButton;

    @FXML public void initialize(){

    }

    @FXML void switchEdit(ActionEvent event) throws IOException{
        System.out.println("dupa");
        helpers.SwitchAnchor("DoctorEdit", event);
    }
}
