package controllers;

import entities.Administrator;
import entities.Doctor;
import entities.Patient;
import entities.User;
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
import repositories.AdministratorRepository;
import repositories.DoctorRepository;
import repositories.PatientRepository;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomescreenController implements Initializable, ControllerPagination {





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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    void Login(ActionEvent event) throws IOException {


        boolean sprawdzenie = false;
        String rola = "";

        PatientRepository ur = new PatientRepository();
        List<Patient> listaU = ur.getAll();

        DoctorRepository dr = new DoctorRepository();
        List<Doctor> listaD = dr.getAll();

        AdministratorRepository ar = new AdministratorRepository();
        List<Administrator> listaA = ar.getAll();
        //Patient u = new Patient(0,"txtLogin.getText()","", "tfPassword.getText()","", "");
        Parent root = null;

        if (!sprawdzenie) {
            for (Patient u1 : listaU) {
                if (u1.getForename().equals(txtLogin.getText()) && u1.getPassword().equals(tfPassword.getText())) {
                    sprawdzenie = true;
                    rola = "patient";


                    // String rol2 = u1.getClass().getName();
                    break;
                }
            }
        }

        if (!sprawdzenie) {
            for (Doctor dl : listaD) {
                if (dl.getForename().equals(txtLogin.getText()) && dl.getPassword().equals(tfPassword.getText())) {
                    sprawdzenie = true;
                    rola = "doctor";
                    // String rol2 = u1.getClass().getName();
                    break;
                }
            }
        }

        if (!sprawdzenie) {
            for (Administrator a1 : listaA) {
                if (a1.getForename().equals(txtLogin.getText()) && a1.getPassword().equals(tfPassword.getText())) {
                    sprawdzenie = true;
                    rola = "admin";
                    // String rol2 = u1.getClass().getName();
                    break;
                }
            }
        }

        switch (rola) {
            case ("admin"):
                helpers.SwitchScene("AdminHome", event);
                break;
            case ("patient"):
                //helpers.SwitchScene("AdminPatient", event);
                DialogBox.warningBox("Warning!", "Pawel L. incomplete module");

                break;
            case ("doctor"):
                helpers.SwitchScene("DoctorMain", event);
                break;
        }
        if (!sprawdzenie) {
            DialogBox.informationBox("Błędne dane", "Podaj poprawne dane do logowanie");
        }

    }

    @FXML
    void Register(ActionEvent event) throws IOException {
           Stage stage;
            helpers.SwitchScene("Register",event);;

            stage = new Stage();
    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

}
