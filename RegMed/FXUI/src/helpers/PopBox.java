package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PopBox {

    public static boolean choiceBox(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, contentText,ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
            return true;
        else return false;
    }
}
