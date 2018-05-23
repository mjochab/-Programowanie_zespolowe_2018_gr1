package controllers.patient;

import dto.PatientModuleDTO;
import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pojo.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientHomeController implements Initializable, ControllerPagination {

    private static Patient patient;

    @FXML
    private Label firstnameLabel,
        lastnameLabel,
        peselLabel,
        emailLabel,
        phoneLabel,
        firstcontactDoctorNameLabel,
        firstcontactDoctorPhoneLabel,
        cityLabel,
        streetLabel,
        numberLabel,
        zipLabel;

    PatientModuleDTO patientModuleDTO;



    public PatientHomeController() {
        this.patientModuleDTO = new PatientModuleDTO();
        patient = patientModuleDTO.get(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPatientData();
    }



    @FXML
    private void registerClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/Registration", event);
    }

    @FXML
    private void historyClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/History", event);
    }







    private void setPatientData() {
        firstnameLabel.setText(patient.getFirstName());
        lastnameLabel.setText(patient.getLastName());
        peselLabel.setText(patient.getPesel());
        emailLabel.setText(patient.getEmail());
        phoneLabel.setText(patient.getPhoneNumber());
        firstcontactDoctorNameLabel.setText(patient.getFirstContactDoctor().getFirstName() + " " + patient.getFirstContactDoctor().getLastName());
        firstcontactDoctorPhoneLabel.setText(patient.getFirstContactDoctor().getPhoneNumber());
        cityLabel.setText(patient.getAddress().getCity());
        streetLabel.setText(patient.getAddress().getStreet());
        numberLabel.setText(Integer.toString(patient.getAddress().getNumber()));
        zipLabel.setText(patient.getAddress().getZip());
    }
}
