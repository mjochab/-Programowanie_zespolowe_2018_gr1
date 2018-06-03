package controllers.doctor;

import dto.PatientListDTO;
import helpers.ControllerPagination;
import htmlParser.ToHtmlParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import pojo.PatientList;


import java.util.HashMap;

public class DoctorReportController implements ControllerPagination {

    @FXML
    Button
        weeklyScheduleButton,
        dailyPatientListButton;

    @FXML
    WebView
        reportWebView;

    @FXML
    WebEngine
        webEngine;

    private PatientListDTO patientListDTO;

    public DoctorReportController() {
        this.patientListDTO = new PatientListDTO();
    }

    @FXML void dailyPatientListOnClick(ActionEvent event){
        String test = dailyPatientList();
        System.out.println(test);
        reportWebView.getEngine().loadContent(test);
    }

    @FXML void weeklyScheduleOnClick(ActionEvent event){

        reportWebView.getEngine().loadContent("<p>dupa</p>");
    }

    private String dailyPatientList(){
        PatientListDTO patientList = new PatientListDTO();
        ToHtmlParser parser = new ToHtmlParser();
        HashMap<String, String> h = new HashMap<>();
        for (PatientList patient: patientList.getPatientList(12)) {
            String hour = patient.getVisitHour();
            String name = patient.getFirstName()+" "+patient.getLastName();
            h.put(hour,name);
        }
        return parser.dailyPatientList(h);
    }
}
