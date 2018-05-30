package helpers;
//http://code.makery.ch/blog/javafx-dialogs-official/

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Class containing static methods for showing popping boxes.
 *
 * @author Szymon P
 */
public class DialogBox {

    /**
     * Alert letting you choice yes or no.
     *
     * @param title         of message.
     * @param headerText    upper part text.
     * @param contentText   lower part text.
     * @return true or false, depending of clicked button.
     */
    public static boolean choiceBox(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, contentText,ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
            return true;
        else return false;
    }

    /**
     * Alert informing about something specified in content text.
     *
     * @param title         title of the alert.
     * @param contentText   text of the alert.
     */
    public static void informationBox(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * Alert warning someone about something described in content text.
     *
     * @param title         title of the alert.
     * @param contentText   text of the alert.
     */
    public static void warningBox(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * Alert informing about wrong data in fields etc.
     *
     * @param headerText    upper part of alert, informing about validation
     *                      problem cause.
     * @param contentText   text of the alert, containing list of validation
     *                      errors.
     */
    public static void validationErrorBox(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation error!");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
