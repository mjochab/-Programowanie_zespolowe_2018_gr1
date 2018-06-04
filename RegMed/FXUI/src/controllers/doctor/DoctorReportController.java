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


import java.time.LocalTime;
import java.util.HashMap;
import java.util.TreeMap;

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

    @FXML
    private void initialize() {
        String test = dailyPatientList();
        reportWebView.getEngine().loadContent(test);
    }


    private String dailyPatientList(){
        PatientListDTO patientList = new PatientListDTO();
        ToHtmlParser parser = new ToHtmlParser();
        TreeMap<LocalTime, String> h = new TreeMap<>();
        for (PatientList patient: patientList.getPatientList(12)) {
            LocalTime hour = patient.getVisitHour();
            String name = patient.getFirstName()+" "+patient.getLastName();
            h.put(hour,name);
        }

        return parser.dailyPatientList(h);
    }
}
