package controllers;

import entities.Patient;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import repositories.RepositoryInterface;
import repositories.UserRepository;


public class AdminPatientController {

    @FXML
    TextField searchField ;

    @FXML
    Button findButton;

    @FXML
    TextField idField;

    @FXML
    TextField peselField;

    @FXML
    TextField forenameField;

    @FXML
    TextField nameField;

    @FXML
    TextField addressField;



    @FXML
    private void initialize() {
        findButton.setOnAction(e -> {



            //peselField.setText(user.getName());
            System.out.println("dupa");
        });
    }









}
