package controllers.homescreen;

import customControls.*;
import dto.PatientAdministrationDTO;
import helpers.ControllerPagination;
import helpers.DialogBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import pojo.Address;
import pojo.Doctor;
import pojo.Patient;

import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterController implements Initializable, ControllerPagination {

    @FXML
    private NameTextField firstnameField,
    lastnameField;

    @FXML
    private PeselTextField peselField;

    @FXML
    private EmailTextField emailField;

    @FXML
    private PhoneTextField phoneField;

    @FXML
    private PasswordField passwordField1,
    passwordField2;



    @FXML
    private CityTextField cityField;

    @FXML
    private StreetTextField streetField;

    @FXML
    private ZipTextField zipField;

    @FXML
    private NumberTextField numberField;

    @FXML
    private ChoiceBox<Doctor> doctorsChoicebox;


    private PatientAdministrationDTO dto;

    private List<Doctor> firstcontactDoctors;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        populateDoctors();
    }

    public RegisterController() {
        dto = new PatientAdministrationDTO();
        firstcontactDoctors = new ArrayList<>(dto.getAllFirstcontactDoctors());


    }

    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException {

        createUser(event);

        passwordField1.setStyle("-fx-text-fill: black;" +
                "-fx-text-box-border: gray ;");
        passwordField2.setStyle("-fx-text-fill: black;" +
                "-fx-text-box-border: gray ;");

    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("homescreen/Homescreen", event);
    }

    private void populateDoctors() {
        doctorsChoicebox.setItems(FXCollections.
                observableArrayList(firstcontactDoctors));
        doctorsChoicebox.getSelectionModel().selectFirst();
    }


    private void createUser(ActionEvent event) {
        Patient result = new Patient();
        Address resultAddress = new Address();

        try {

            result.setFirstName(firstnameField.getTextValidated());
            result.setLastName(lastnameField.getTextValidated());
            result.setPesel(peselField.getTextValidated());
            result.setEmail(emailField.getTextValidated());
            result.setPhoneNumber(phoneField.getTextValidated());

            if (!passwordField1.getText().equals(passwordField2.getText()) ||
                    passwordField1.getText().isEmpty() || passwordField2.getText().isEmpty()) {
                notSamePasswordshandle();
            }
            result.setPassword(passwordField2.getText());

            resultAddress.setCity(cityField.getTextValidated());
            resultAddress.setStreet(streetField.getTextValidated());
            resultAddress.setZip(zipField.getTextValidated());
            resultAddress.setNumber(Integer.parseInt(numberField.getTextValidated()));
            result.setAddress(resultAddress);

            result.setFirstContactDoctor(doctorsChoicebox.getSelectionModel().getSelectedItem());

            dto.add(result);

            DialogBox.informationBox("Success!", "Registration successful.");
            helpers.SwitchScene("homescreen/Homescreen", event);

        } catch (CustomControlsException | ArrayIndexOutOfBoundsException ex) {
            if(ex.getClass() == ArrayIndexOutOfBoundsException.class) {
                DialogBox.validationErrorBox("Wrong patient data!",
                        "Please choose firstcontact doctor");
            } else {
                DialogBox.validationErrorBox("Wrong patient data!", ex.getMessage());
            }
        } catch (IOException e) {

        }
    }

    private void notSamePasswordshandle() throws CustomControlsException {
        passwordField1.setStyle("-fx-text-fill: red;" +
                "-fx-text-box-border: red ;");
        passwordField2.setStyle("-fx-text-fill: red;" +
                "-fx-text-box-border: red ;");
        passwordField1.setText(null);
        passwordField2.setText(null);
        throw new CustomControlsException("Passwords are not the same!");

    }




}




