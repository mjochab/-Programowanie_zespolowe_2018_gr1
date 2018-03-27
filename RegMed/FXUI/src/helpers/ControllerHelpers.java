package helpers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerHelpers {

    public void SwitchScene(String childView, ActionEvent event) throws IOException {
        Parent child = FXMLLoader.load(getClass().getResource("../views/" + childView + ".fxml"));
        Scene childScene = new Scene(child);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(childScene);
        window.show();
    }

}
