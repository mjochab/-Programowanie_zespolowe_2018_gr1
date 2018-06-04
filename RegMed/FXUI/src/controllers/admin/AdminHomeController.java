package controllers.admin;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pojo.Administrator;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminHomeController implements ControllerPagination,Initializable {

    @FXML
    private Label lLogged;





    public static Administrator a;

    //ControllerHelpers helpers = new ControllerHelpers();

    public void patientsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/PatientAdministration", event);
    }

    public void doctorsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/DoctorAdministration", event);
    }

    public void administratorsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/AdminAdministration", event);
    }

    public void doctorsModuleButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("doctor/DoctorMain", event);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lLogged.setText(a.getFirstName() + " " + a.getLastName());
    }
}
