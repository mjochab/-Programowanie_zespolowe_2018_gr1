package controllers.patient;

import dto.PatientModuleDTO;
import helpers.ControllerPagination;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import pojo.SingleVisit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable, ControllerPagination {

    PatientModuleDTO patientModuleDTO;

    @FXML
    private DatePicker visitDatePicker;

    @FXML
    private ChoiceBox<SingleVisit> visitHourPicker;
    //private ChoiceBox<String> visitHourPicker;

    public RegistrationController() {
        patientModuleDTO = new PatientModuleDTO();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBusyVisits();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/PatientHome", event);
    }


    private void loadBusyVisits() {
        ArrayList list = new ArrayList(parseVisitsToHour(patientModuleDTO.getFreeVisits(patientModuleDTO.getAllAdmissionDays().get(0))));
        visitHourPicker.setItems(FXCollections.observableArrayList(list));

    }

    private List<String> parseVisitsToHour(List<SingleVisit> visits) {
        List<String> list = new ArrayList<>();

        for (SingleVisit visit : visits) {
            list.add(visit.visitHourToString());
        }

        return list;
    }

}
