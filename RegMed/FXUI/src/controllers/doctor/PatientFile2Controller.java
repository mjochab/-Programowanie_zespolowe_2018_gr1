package controllers.doctor;


import controllers.homescreen.HomescreenController;
import dto.*;
import helpers.DialogBox;
import htmlParser.ToHtmlDoctor;
import htmlParser.ToHtmlParser;
import htmlParser.ToHtmlPatient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import pojo.Doctor;
import pojo.File;
import pojo.Patient;
import pojo.PatientsHistory;

import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import static controllers.doctor.PatientFile1Controller.getSelectedPatientId;

public class PatientFile2Controller implements Initializable {

    ///////////////
    private ObservableList<String> specializationsList;
    private PatientModuleDTO patientModuleDTO;
    private ObservableList<DoctorWorkingDays> doctorWorkingDaysTableData;
    @FXML
    private TextArea
            recognition;


    ///////////////
    @FXML
    Button
            addTextButton,
            historyButton;

    @FXML
    TabPane
            tabPane;

    @FXML
    WebView
            historyOfPatient;
    @FXML
    ChoiceBox<Specialization>
            specializationChoiceBox;



    private PatientsHistoryDTO patientsHistoryDTO;

    private int doctorId = 12;
    private int specialistId = 3;
    private int specializationId=0;
    public PatientFile2Controller() {
        this.patientsHistoryDTO = new PatientsHistoryDTO();
        this.patientsRefferalDTO = new PatientsRefferalDTO();
        patientModuleDTO = new PatientModuleDTO();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeTab();
        loadDataIntoSpecializationAndDoctorsChoiceBoxes();
    }

    public void changeTab() {
            tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
                if (addTextButton.isVisible()) {
                    addTextButton.setVisible(false);
                    historyButton.setVisible(true);
                } else {
                    addTextButton.setVisible(true);
                    historyButton.setVisible(false);
                }
            });
    }

    public void loadHistoryButtonClick(ActionEvent event){
        try{
            historyOfPatient.getEngine().loadContent(showPatientFile(getSelectedPatientId));
        }catch (NullPointerException e){
            DialogBox.warningBox("Warning!", "Please select patient from the table!");
        }
    }

    public void addTextButtonClicked(ActionEvent event) {
        String reco = recognition.getText();
        if(getSelectedPatientId!=null){
            if(reco.length()!=0){
                insertPatientsFile(getSelectedPatientId, reco);
            }
        }
        recognition.clear();
    }

    public void addRefferalButtonClicked(ActionEvent event){
        String  reff = refferalforpatient.getText();
        if(getSelectedPatientId!=null){
            if(reff.length()!=0){
                insertPatientsRefferal(getSelectedPatientId, reff);
            }
        }
        refferalforpatient.clear();
    }

    public void insertPatientsRefferal(int patientId, String refferalforpatient){
        PatientsRefferal refferalAdd = new PatientsRefferal();
        refferalAdd.setPatientId(patientId);
        refferalAdd.setDoctorId(doctorId);
        refferalAdd.setSpecialistId(specializationChoiceBox.getSelectionModel().getSelectedItem().getId());
        refferalAdd.setRefferalforpatient(refferalforpatient);
        patientsRefferalDTO.add(refferalAdd);
    }



    public void insertPatientsFile(int patientId, String recognition) {
        PatientsHistory historyAdd = new PatientsHistory();
        historyAdd.setPatientId(patientId);
        historyAdd.setDoctorId(doctorId);
        historyAdd.setRecognition(recognition);
        patientsHistoryDTO.add(historyAdd);
    }



    private void loadDataIntoSpecializationAndDoctorsChoiceBoxes() {
        specializationChoiceBox.setItems(FXCollections.observableArrayList(patientModuleDTO.getSpecializations()));
    }








    public String showPatientFile(int patientId) {
        PatientAdministrationDTO patientDTO = new PatientAdministrationDTO();
        DoctorAdministrationDTO doctorDTO = new DoctorAdministrationDTO();
        Patient patientData = patientDTO.get(patientId);
        Doctor doctorData = doctorDTO.get(doctorId);
        ToHtmlPatient patient = null;
        ToHtmlDoctor doctor = null;
        ToHtmlParser toHtmlParser = new ToHtmlParser();
        TreeMap<LocalDateTime, String> h = new TreeMap<>();
        String out = "";
        try {
            patient = new ToHtmlPatient(patientData.getFirstName(),
                    patientData.getLastName(),
                    patientData.getPesel(),
                    patientData.getAddress().getZip(),
                    patientData.getAddress().getCity(),
                    patientData.getAddress().getStreet(),
                    Integer.toString(patientData.getAddress().getNumber()),
                    patientData.getPhoneNumber());

            doctor = new ToHtmlDoctor(doctorData.getFirstName(),
                    doctorData.getLastName());


            FileDTO file = new FileDTO();
            List<File> fileData = file.getFiles(getSelectedPatientId);
            for(File fileTemp: fileData){
                h.put(fileTemp.getDate(),fileTemp.getHistory());
            }
            out = toHtmlParser.patietFile(patient, doctor, h);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            historyOfPatient.getEngine().loadContent("History is empty");
        }
        return out;
    }


}
