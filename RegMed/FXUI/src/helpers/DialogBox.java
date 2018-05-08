package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

//http://code.makery.ch/blog/javafx-dialogs-official/

public class DialogBox {

    public static boolean choiceBox(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, contentText,ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
            return true;
        else return false;
    }

    public static void informationBox(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void warningBox(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void validationErrorBox(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation error!");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
