package controllers;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;

public class AdminHomeController implements ControllerPagination {

    //ControllerHelpers helpers = new ControllerHelpers();

    public void patientsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("AdminPatient", event);
    }

    public void doctorsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("AdminDoctor", event);
    }

    public void administratorsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("AdminAdministrator", event);
    }

    public void doctorsModuleButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("DoctorMain", event);
    }

}
