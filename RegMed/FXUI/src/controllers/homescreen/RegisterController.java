package controllers.homescreen;



import dto.DoctorAdministrationDTO;
import dto.PatientAdministrationDTO;
import helpers.ControllerPagination;
import helpers.DialogBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import models.ValidatorModel;
import pojo.Address;
import pojo.Doctor;
import pojo.Patient;

import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;

public class RegisterController implements Initializable, ControllerPagination {

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfPostalCode;

    @FXML
    private PasswordField tfPassword2;

    @FXML
    private PasswordField tfPassword1;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfSurname;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPesel;

    @FXML
    private TextField tfStreet;
    @FXML
    private ComboBox<Doctor> cbDoctor;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfPhone;

    @FXML
    private Button bBack;

    private ObservableList<Doctor> dataDoctors;
    private ObservableList<Doctor> dataDoctors2;



    @FXML
    void register(ActionEvent event) {

        if (ValidatorModel.containsOnlyLetters(tfCity.getText())) {
            if (ValidatorModel.containsOnlyLetters(tfName.getText())) {
                if (ValidatorModel.containsOnlyLetters(tfSurname.getText())) {
                    if (ValidatorModel.peselValidator(tfPesel.getText())) {
                        if (ValidatorModel.postalCodeValidator(tfPostalCode.getText())) {
                            if (ValidatorModel.phoneValidator(tfPhone.getText())) {
                                if (ValidatorModel.emailValidator(tfEmail.getText())) {
                                    if (tfPassword1.getText().equals(tfPassword2.getText())) {
                                        if (ValidatorModel.streetValidator(tfStreet.getText()) &&
                                                ValidatorModel.containsOnlyNumbers(tfNumber.getText())) {
                                            PatientAdministrationDTO newPatient = new PatientAdministrationDTO();
                                            Patient p = new Patient();
                                            p.setEmail(tfEmail.getText());
                                            p.setFirstName(tfName.getText());
                                            p.setLastName(tfSurname.getText());
                                            p.setPassword(tfPassword1.getText());
                                            p.setPassword(tfPassword2.getText());
                                            p.setPesel(tfPesel.getText());
                                            p.setPhoneNumber(tfPhone.getText());
                                            Address ad = new Address();
                                            ad.setCity(tfCity.getText());
                                            ad.setStreet(tfStreet.getText());
                                            ad.setZip(tfPostalCode.getText());
                                            ad.setNumber(Integer.parseInt(tfNumber.getText()));
                                            p.setAddress(ad);
                                            p.setFirstContactDoctorId(cbDoctor.getSelectionModel().getSelectedItem().getDoctorId());
                                            newPatient.add(p);
                                            try {
                                                ControllerPagination.helpers.SwitchScene("homescreen/Homescreen", event);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            DialogBox.informationBox("Błędne dane", "Zły adres");
                                        }
                                    } else {
                                        DialogBox.informationBox("Błędne dane", "Hasła są różne");
                                    }
                                } else {
                                    DialogBox.informationBox("Błędne dane", "Nieprawidłowy e-mail");
                                }
                            } else {
                                DialogBox.informationBox("Błędne dane", "Nieprawidłowy numer telefonu");
                            }
                        } else {
                            DialogBox.informationBox("Błędne dane", "Nieprawidłowy kod pocztowy");
                        }
                    } else {
                        DialogBox.informationBox("Błędne dane", "Nieprawidłowy pesel");
                    }
                } else {
                    DialogBox.informationBox("Błędne dane", "Nieprawidłowe nazwisko.");
                }
            } else {
                DialogBox.informationBox("Błędne dane", "Nieprawidłowe imie." );
            }
        } else {
            DialogBox.informationBox("Błędne dane", "Niepoprawne miasto.");
        }
    }

    @FXML
    void exit(ActionEvent event) {
        // Stage stage = (Stage) bBack.getScene().getWindow();
        try {
            //stage.close();
            ControllerPagination.helpers.SwitchScene("homescreen/Homescreen", event);

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorAdministrationDTO dadto = new DoctorAdministrationDTO();

        dataDoctors = FXCollections.observableArrayList();
        dataDoctors2 = FXCollections.observableArrayList();

        dataDoctors.addAll(dadto.getAll());

        for (Doctor doct : dataDoctors) {
            if (doct.getSpecializationId() == 21) dataDoctors2.add(doct);
        }

        //dataDoctors.addAll(dadto.getAll());
        cbDoctor.setItems(dataDoctors2);


        cbDoctor.setCellFactory(new Callback<ListView<Doctor>,ListCell<Doctor>>(){
                            @Override
                            public ListCell<Doctor> call(ListView<Doctor> l){
                                return new ListCell<Doctor>(){
                                    @Override
                                    protected void updateItem(Doctor item, boolean empty) {
                                        super.updateItem(item, empty);
                                        if (item != null) {
                                            setText(item.getFirstName() + " " + item.getLastName());
                                        }
                    }
                } ;
            }
        });


        cbDoctor.setConverter(new StringConverter<Doctor>() {
            @Override
            public String toString(Doctor user) {
                if (user == null){
                    return null;
                } else {
                    return user.getFirstName() + " " + user.getLastName();
                }
            }

            @Override
            public Doctor fromString(String userId) {
                return null;
            }
        });


    }
}







