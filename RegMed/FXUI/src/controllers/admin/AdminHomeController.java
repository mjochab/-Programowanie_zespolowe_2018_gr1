package controllers.admin;

import controllers.doctor.DoctorMainController;
import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pojo.Administrator;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminHomeController implements ControllerPagination,Initializable {

    @FXML
    private Label lLogged;


    @FXML
    private Button bLogout;





    public static Administrator a;

    //ControllerHelpers helpers = new ControllerHelpers();

    public void patientsButtonClicked(ActionEvent event) throws IOException {
        PatientAdministrationController.administrator = a;
        helpers.SwitchScene("admin/PatientAdministration", event);
    }

    public void doctorsButtonClicked(ActionEvent event) throws IOException {
        DoctorAdministrationController.administrator = a;
        helpers.SwitchScene("admin/DoctorAdministration", event);
    }

    public void administratorsButtonClicked(ActionEvent event) throws IOException {
        AdminAdministrationController.administrator = a;
        helpers.SwitchScene("admin/AdminAdministration", event);
    }

    public void doctorsModuleButtonClicked(ActionEvent event) throws IOException {
        DoctorMainController.administrator = a;
        helpers.SwitchScene("doctor/DoctorMain", event);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lLogged.setText( "Logged as: " +a.getFirstName() + " " + a.getLastName());
    }


    @FXML
    void logout(ActionEvent event) throws IOException {
        a = null;
        helpers.SwitchScene("homescreen/Homescreen", event);
    }


}
