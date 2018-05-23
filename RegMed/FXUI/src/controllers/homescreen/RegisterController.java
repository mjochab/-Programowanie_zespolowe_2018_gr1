package controllers.homescreen;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterController implements Initializable, ControllerPagination {


    @FXML
    private Button bBack;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
/**
 * method closing the stage.
*/
    @FXML
    void Exit(ActionEvent event) {
        // Stage stage = (Stage) bBack.getScene().getWindow();
        try {
            //stage.close();
            helpers.SwitchScene("homescreen/Homescreen", event);

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }
}




