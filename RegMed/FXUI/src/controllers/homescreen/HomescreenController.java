package controllers.homescreen;

import dto.AdminAdministrationDTO;
import dto.DoctorAdministrationDTO;
import dto.PatientAdministrationDTO;
import helpers.ControllerPagination;
import helpers.DialogBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Administrator;
import pojo.Doctor;
import pojo.Patient;
import pojo.User;
import repositories.AdministratorRepository;
import repositories.DoctorRepository;
import repositories.PatientRepository;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The homescreen class that you use to log in
 *
 * @author student
 */

public class HomescreenController implements ControllerPagination {


    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button bExit;

    @FXML
    private Button bLogin;

    @FXML
    private Button bRegister;

    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Administrator> administrators;

    @FXML
    public void initialize() {
        PatientAdministrationDTO patientsDTO = new PatientAdministrationDTO();
        DoctorAdministrationDTO doctorsDTO = new DoctorAdministrationDTO();
        AdminAdministrationDTO adminsDTO = new AdminAdministrationDTO();
        patients = patientsDTO.getAll();
        doctors = doctorsDTO.getAll();
        administrators = adminsDTO.getAll();
    }

    /**
     * method for logging in
     * checking data with the database
     * Display of the corresponding window for data from the database
     *
     * @param event
     * @throws IOException
     */

    public static int loggedUser;

    @FXML
    void Login(ActionEvent event) throws IOException {
        checkUser(txtLogin.getText(), tfPassword.getText(), event);

    }

    /**
     * method that opens a new scene for registration
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void Register(ActionEvent event) throws IOException {
        Stage stage;
        helpers.SwitchScene("homescreen/Register", event);

        stage = new Stage();
    }

    /**
     * method closing the stage.
     *
     * @param event
     */
    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    private void checkUser(String pesel, String password, ActionEvent event) throws IOException {
        Patient loggedPatient = null;
        Doctor loggedDoctor = null;
        Administrator loggedAdministrator = null;

        boolean exit = false;

        for (Patient patient : patients) {
            if (patient.getPesel().equals(pesel) && patient.getPassword().equals(password)) {
                loggedPatient = patient;
                exit = true;

                break;
            }
        }
        if (!exit) {
            for (Doctor doctor : doctors) {
                if (doctor.getPesel().equals(pesel) && doctor.getPassword().equals(password)) {
                    loggedDoctor = doctor;
                    exit = true;

                    break;
                }
            }
        }
        if (!exit) {
            for (Administrator admin : administrators) {
                if (admin.getPesel().equals(pesel) && admin.getPassword().equals(password)) {
                    loggedAdministrator = admin;
                    exit = true;

                    break;
                }
            }
        }
        if (!exit) {
            DialogBox.warningBox("Attention!", "Wrong user Login or Password");
        }
        if (loggedPatient != null) {
            loggedUser=loggedPatient.getId();
            helpers.SwitchScene("patient/PatientHome", event);
        }
        if (loggedDoctor != null) {
            loggedUser=loggedDoctor.getId();
            helpers.SwitchScene("doctor/DoctorMain", event);
        }
        if (loggedAdministrator != null) {
            loggedUser=loggedAdministrator.getId();
            helpers.SwitchScene("admin/AdminHome", event);
        }
    }
}

