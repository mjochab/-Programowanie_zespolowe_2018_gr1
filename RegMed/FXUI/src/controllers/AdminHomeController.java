package controllers;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;


import java.io.IOException;

public class AdminHomeController implements ControllerPagination {

    //ControllerHelpers helpers = new ControllerHelpers();

    public void patientsButtonClicked(ActionEvent event) throws IOException {
        System.out.println("patientsButtonClicked");



        helpers.SwitchScene("AdminPatient", event);
    }

    public void doctorsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("DoctorMain", event);
    }

}
