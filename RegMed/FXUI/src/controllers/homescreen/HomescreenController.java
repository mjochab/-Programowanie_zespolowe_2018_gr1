package controllers.homescreen;

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

/**
 * The homescreen class that you use to log in
 * 
 * @author student
 */

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
/**
 * method for logging in
 * checking data with the database
 * Display of the corresponding window for data from the database
 * @param event
 * @throws IOException 
 */

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
                helpers.SwitchScene("admin/AdminHome", event);
                break;
            case ("patient"):
                helpers.SwitchScene("patient/PatientHome", event);
                break;
            case ("doctor"):
                helpers.SwitchScene("doctor/DoctorMain", event);
                break;
        }
        if (!sprawdzenie) {
            DialogBox.informationBox("BĹ‚Ä™dne dane", "Podaj poprawne dane do logowanie");
        }

    }
 /**
  * method that opens a new scene for registration
  * @param event
  * @throws IOException 
  */
    @FXML
    void Register(ActionEvent event) throws IOException {
           Stage stage;
            helpers.SwitchScene("homescreen/Register",event);;

            stage = new Stage();
    }
/**
 * method closing the stage.
 * @param event 
 */
    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

}
