package controllers.patient;

import dto.FileDTO;
import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 *
 */
public class HistoryController implements ControllerPagination, Initializable {

    private static int PATIENT_ID;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab perscriptionsTab,
        referralsTab,
        historyTab;

    FileDTO dto;



    public HistoryController() {
        this.dto = new FileDTO();
        PATIENT_ID = 1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void historyTabClicked() {
        List<File> files = new ArrayList(dto.getFiles(PATIENT_ID));
        
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/PatientHome", event);
    }


}
