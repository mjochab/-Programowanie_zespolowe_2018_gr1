package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class DoctorEditController {



    @FXML
    CheckBox
            mondayBox,
            tuesdayBox,
            wednesdayBox,
            thursdayBox,
            fridayBox;

    @FXML
    TextField
            mondayFromTextField,
            mondayToTextField,
            tuesdayFromTextField,
            tuesdayToTextField,
            wednesdayFromTextField,
            wednesdayToTextField,
            thursdayFromTextField,
            thursdayToTextField,
            fridayFromTextField,
            fridayToTextField,

            intervalTextField;

    @FXML
    Button
            okButton,
            cancelButton;

    public void mondayClickHandler(ActionEvent event) {
        if (!mondayFromTextField.isVisible()) {
            mondayFromTextField.setVisible(true);
            mondayToTextField.setVisible(true);
        } else {
            mondayFromTextField.setVisible(false);
            mondayToTextField.setVisible(false);
        }
    }

//    public void tuesdayClickHandler(ActionEvent event) {
//        if (!tuesdayFromTextField.isVisible()) {
//            tuesdayFromTextField.setVisible(true);
//            tuesdayToTextField.setVisible(true);
//        } else {
//            tuesdayFromTextField.setVisible(false);
//            tuesdayToTextField.setVisible(false);
//        }
//    }
//
//    public void wednesdayClickHandler(ActionEvent event) {
//        if (!wednesdayFromTextField.isVisible()) {
//            wednesdayFromTextField.setVisible(true);
//            wednesdayToTextField.setVisible(true);
//        } else {
//            wednesdayFromTextField.setVisible(false);
//            wednesdayToTextField.setVisible(false);
//        }
//    }
//
//    public void thursdayClickHandler(ActionEvent event) {
//        if (!thursdayFromTextField.isVisible()) {
//            thursdayFromTextField.setVisible(true);
//            thursdayToTextField.setVisible(true);
//        } else {
//            thursdayFromTextField.setVisible(false);
//            thursdayToTextField.setVisible(false);
//        }
//    }
//
//    public void fridayClickHandler(ActionEvent event) {
//        if (!fridayFromTextField.isVisible()) {
//            fridayFromTextField.setVisible(true);
//            fridayToTextField.setVisible(true);
//        } else {
//            fridayFromTextField.setVisible(false);
//            fridayToTextField.setVisible(false);
//        }
//    }

}
