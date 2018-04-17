package controllers;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable, ControllerPagination {



    @FXML
    private Button bBack;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }






        @FXML
        void Exit(ActionEvent event) {
            Stage stage = (Stage) bBack.getScene().getWindow();
            stage.close();
        }

    }


