package controllers.admin;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;

public class AdminHomeController implements ControllerPagination {

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
}
