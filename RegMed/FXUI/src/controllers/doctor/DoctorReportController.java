package controllers.doctor;

import dto.PatientListDTO;
import helpers.ControllerPagination;
import htmlParser.ToHtmlParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


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
        reportWebView.getEngine().loadContent(dailyPatientList(), "text/html");
    }

    @FXML void weeklyScheduleOnClick(ActionEvent event){

        reportWebView.getEngine().loadContent("<p>dupa</p>");
    }

    private String dailyPatientList(){
        PatientListDTO patientList = new PatientListDTO();
        ToHtmlParser parser = new ToHtmlParser();
        String hour = patientList.getPatientList(12).getVisitHour();
        String name = patientList.getPatientList(12).getFirstName()+" "+patientList.getPatientList(12).getLastName();
        HashMap<String, String> h = new HashMap<>();
        h.put(hour,name);
        System.out.println(parser.dailyPatientList(h));
        return parser.dailyPatientList(h);
    }
}
