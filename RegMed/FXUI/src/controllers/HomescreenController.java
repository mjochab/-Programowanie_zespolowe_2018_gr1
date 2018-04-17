package controllers;

import entities.Administrator;
import entities.Doctor;
import entities.Patient;
import entities.User;
import helpers.ControllerPagination;
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
                helpers.SwitchScene("AdminAdministrator",event);
                break;
            case ("patient"):
                helpers.SwitchScene("AdminPatient",event);

                break;
            case ("doctor"):
                helpers.SwitchScene("AdminDoctor",event);
                break;
        }


    /*
    if (getString("login").equals(txtLogin.getText())) {
        if (rs.getString("haslo").equals(tfPassword.getText())) {
            sprawdzenie = true;
            rola = "patient";
            break;
        }*/

    /*
    try {

            Connection con = null;

            PreparedStatement ps = null;





            ResultSet rs = null;

            boolean sprawdzenie = false;
            String rola = "";


            ps = con.prepareStatement("SELECT login,haslo FROM admin");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("login").equals(txtLogin.getText())) {
                    if (rs.getString("haslo").equals(tfPassword.getText())) {
                        sprawdzenie = true;
                        rola = "admin";
                        break;
                    }
                }
            }


            ps = con.prepareStatement("SELECT login,haslo FROM patient");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("login").equals(txtLogin.getText())) {
                    if (rs.getString("haslo").equals(tfPassword.getText())) {
                        sprawdzenie = true;
                        rola = "patient";
                        break;
            }
            }
            }


            ps = con.prepareStatement("SELECT login,haslo FROM doctor");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("login").equals(txtLogin.getText())) {
                    if (rs.getString("haslo").equals(tfPassword.getText())) {
                        sprawdzenie = true;
                        rola = "doctor";
                        break;
            }
            }
            }


            if (sprawdzenie) {
            System.out.println("Logowanie przebiegło pomyślnie!");






            Parent root = null;
            switch (rola) {
            case ("admin"):
            root = FXMLLoader.load(LoginViewController.class.getResource("admin.fxml"));
        break;
        case ("patient"):
        root = FXMLLoader.load(LoginViewController.class.getResource("patient.fxml"));
        break;
        case ("doctor"):
        root = FXMLLoader.load(LoginViewController.class.getResource("doctor.fxml"));
        break;
        }
        stage = new Stage();



        Scene scene = new Scene(root);
        stage.setScene(scene);
        bLogin.getScene().getWindow().hide();
        stage.show();
        txtLogin.setText(null);
        tfPassword.setText(null);
        //}
        }



        ps.close();
        rs.close();
        con.close();
        } catch (SQLException ex) {
        System.out.println("Błąd SQL!");
        } catch (IOException ex) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
*/

    }

    @FXML
    void Register(ActionEvent event) throws IOException {
           Stage stage;
            Parent root = FXMLLoader.load(getClass().getResource("../views/Register.fxml"));

            stage = new Stage();

    stage.setScene(new Scene(root));
        //helpers.SwitchScene("Register",event);
            stage.setTitle("Register window");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(bRegister.getScene().getWindow());
            stage.showAndWait();

    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

}
