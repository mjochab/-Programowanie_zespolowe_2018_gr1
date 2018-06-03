package controllers.doctor;


import dto.PatientsHistoryDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import pojo.PatientsHistory;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.doctor.PatientFile1Controller.getSelectedPatientId;

public class PatientFile2Controller implements Initializable{

    ///////////////

    @FXML
    private TextArea
            recognition ,
            showHistory ;



    ///////////////
    @FXML
    Button
          addTextButton ;


    @FXML
    TabPane
        tabPane;

    private PatientsHistoryDTO patientsHistoryDTO;

    public PatientFile2Controller() {
        this.patientsHistoryDTO = new PatientsHistoryDTO();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeTab();
    }


    public void changeTab(){
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if(addTextButton.isVisible()){
                addTextButton.setVisible(false);
            }else{
                addTextButton.setVisible(true);
            }
        });
    }

    public void addTextButtonClicked(ActionEvent event)  {

    String reco = recognition.getText();

    insertPatientsFile(reco);




//        showHistory.setText(recognition.getText());

//            String  text = showHistory.getText();
//            text+= recognition.getText();
//            showHistory.setText(text);
            recognition.clear();

    }


    public void insertPatientsFile(String recognition){
        PatientsHistory historyAdd = new PatientsHistory();
        historyAdd.setPatientId(getSelectedPatientId );
        historyAdd.setDoctorId(1);
        historyAdd.setRecognition(recognition);
        patientsHistoryDTO.add(historyAdd);

    }









}
