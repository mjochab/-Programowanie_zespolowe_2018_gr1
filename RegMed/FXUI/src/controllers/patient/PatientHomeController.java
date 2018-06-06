package controllers.patient;

import controllers.homescreen.HomescreenController;
import dto.PatientModuleDTO;
import helpers.ControllerPagination;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import pojo.Patient;
import pojo.SingleVisit;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PatientHomeController implements Initializable, ControllerPagination {

    private int loggedUserId = HomescreenController.loggedUser;
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

    @FXML
    private ListView<SingleVisit> visitsListView;


    PatientModuleDTO patientModuleDTO;



    public PatientHomeController() {
        this.patientModuleDTO = new PatientModuleDTO();
        patient = patientModuleDTO.get(loggedUserId);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPatientData();
        setVisitCellFactory();
        loadDataToVisits();
    }


    private void loadDataToVisits() {
        List<SingleVisit> patientVisits = patientModuleDTO.getVisitsAfterToday(patient);
        visitsListView.setItems(FXCollections.observableArrayList(patientVisits));
    }

    private void setVisitCellFactory() {


        visitsListView.setCellFactory(param -> new ListCell<SingleVisit>() {
            @Override
            protected void updateItem(SingleVisit item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {

                    if (item.getAdmissionDay2().getDate().isEqual(LocalDate.now())) {
                        setText(String.format("%s %d:%d - %s, %s %s",
                                item.getAdmissionDay2().getDate(),
                                item.getVisitHour().getHour(),
                                item.getVisitHour().getMinute(),
                                item.getAdmissionDay2().getDoctor().getSpecialization().getName(),
                                item.getAdmissionDay2().getDoctor().getFirstName(),
                                item.getAdmissionDay2().getDoctor().getLastName()
                        ));
                        setStyle("-fx-background-color: rgba(21,218,255,0.3);" +
                                "-fx-font-weight: bold;");
                    }

                    setText(String.format("%s %d:%d - %s, %s %s",
                            item.getAdmissionDay2().getDate(),
                            item.getVisitHour().getHour(),
                            item.getVisitHour().getMinute(),
                            item.getAdmissionDay2().getDoctor().getSpecialization().getName(),
                            item.getAdmissionDay2().getDoctor().getFirstName(),
                            item.getAdmissionDay2().getDoctor().getLastName()
                    ));

                }
            }
        });
    }









    @FXML
    private void registerClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/Registration", event);
    }

    @FXML
    private void historyClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/History", event);
    }


    @FXML
    private void logoutButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("homescreen/Homescreen", event);
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
