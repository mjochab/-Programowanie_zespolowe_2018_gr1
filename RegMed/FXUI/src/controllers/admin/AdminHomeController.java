package controllers.admin;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;

/**
 * Controller holding links to all administration module actions/pages.
 *
 * @author Szymon P
 */
public class AdminHomeController implements ControllerPagination {

    //ControllerHelpers helpers = new ControllerHelpers();

    /**
     * Switching scene to patients administration page.
     *
     * @param event         using by pagination helper for get current scene.
     *                      It is necessary to switch from one scene to another.
     * @see helpers.ControllerHelpers
     * @throws IOException  throwing when fxml file wasn't found
     */
    public void patientsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/PatientAdministration", event);
    }

    /**
     * Switching scene to doctors administration page.
     *
     * @param event         using by pagination helper for get current scene.
     *                      It is necessary to switch from one scene to another.
     * @see helpers.ControllerHelpers
     * @throws IOException  throwing when fxml file wasn't found
     */
    public void doctorsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/DoctorAdministration", event);
    }

    /**
     * Switching scene to administrators administration page.
     *
     * @param event         using by pagination helper for get current scene.
     *                      It is necessary to switch from one scene to another.
     * @see helpers.ControllerHelpers
     * @throws IOException  throwing when fxml file wasn't found
     */
    public void administratorsButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("admin/AdminAdministration", event);
    }

}
