package controllers.admin;

import controllers.homescreen.HomescreenController;
import dto.AdminAdministrationDTO;
import helpers.ControllerPagination;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller holding links to all administration module actions/pages.
 *
 * @author Szymon P
 */
public class AdminHomeController implements ControllerPagination, Initializable {

    private int loggedAdminId = HomescreenController.loggedUser;
    AdminAdministrationDTO dto;

    @FXML
    private Label loggedAdminLabel;


    public AdminHomeController() {
        dto = new AdminAdministrationDTO();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String txt = String.format("%s %s", dto.get(loggedAdminId).getFirstName(),
                dto.get(loggedAdminId).getLastName());
        loggedAdminLabel.setText(txt);
    }



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

    @FXML
    private void logoutButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("homescreen/Homescreen", event);
    }


}
