package controllers.doctor;


import dto.PatientsHistoryDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import pojo.PatientsHistory;

public class PatientFile2Controller {

    ///////////////

    @FXML
    private TextArea
            recognition ,
            showHistory ;



    ///////////////
    @FXML
    Button
          addTextButton ;
//        editTextButton;

    private PatientsHistoryDTO patientsHistoryDTO;

    public PatientFile2Controller() {
        this.patientsHistoryDTO = new PatientsHistoryDTO();
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
        historyAdd.setPatientId(1);
        historyAdd.setDoctorId(1);
        historyAdd.setRecognition(recognition);
        patientsHistoryDTO.add(historyAdd);

    }









}
