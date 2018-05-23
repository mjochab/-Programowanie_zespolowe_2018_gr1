package controllers.patient;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements ControllerPagination, Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab perscriptionsTab,
        referralsTab;


    public HistoryController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/PatientHome", event);
    }


}
