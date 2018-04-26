package controllers.doctor;

import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import models.DoctorEditModel;
import models.ValidatorModel;

import java.io.IOException;
import java.util.HashMap;

public class DoctorEditController implements ControllerPagination{



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

    @FXML
    BorderPane
            borderPane;

    public void mondayClickHandler(ActionEvent event) {
        mondayFromTextField.setDisable(!mondayBox.isSelected());
        mondayToTextField.setDisable(!mondayBox.isSelected());
    }

    public void tuesdayClickHandler(ActionEvent event) {
        tuesdayFromTextField.setDisable(!tuesdayBox.isSelected());
        tuesdayToTextField.setDisable(!tuesdayBox.isSelected());
    }

    public void wednesdayClickHandler(ActionEvent event) {
        wednesdayFromTextField.setDisable(!wednesdayBox.isSelected());
        wednesdayToTextField.setDisable(!wednesdayBox.isSelected());
    }

    public void thursdayClickHandler(ActionEvent event) {
        thursdayFromTextField.setDisable(!thursdayBox.isSelected());
        thursdayToTextField.setDisable(!thursdayBox.isSelected());
    }

    public void fridayClickHandler(ActionEvent event) {
        fridayFromTextField.setDisable(!fridayBox.isSelected());
        fridayToTextField.setDisable(!fridayBox.isSelected());
    }

    public void okButtonOnClick(ActionEvent event){
        HashMap<String, DoctorEditModel> hours = new HashMap<String, DoctorEditModel>();
        if(ValidatorModel.doctorEditDayValidator(mondayBox.isSelected(),mondayFromTextField.getText(),mondayToTextField.getText())) {
            hours.put("monday", new DoctorEditModel(mondayBox.isSelected(), mondayFromTextField.getText(), mondayToTextField.getText()));
        }
        if(ValidatorModel.doctorEditDayValidator(tuesdayBox.isSelected(),tuesdayFromTextField.getText(),tuesdayToTextField.getText())) {
            hours.put("tuesday", new DoctorEditModel(tuesdayBox.isSelected(), tuesdayFromTextField.getText(), tuesdayToTextField.getText()));
        }
        if(ValidatorModel.doctorEditDayValidator(wednesdayBox.isSelected(),wednesdayFromTextField.getText(),wednesdayToTextField.getText())) {
            hours.put("wednesday", new DoctorEditModel(wednesdayBox.isSelected(), wednesdayFromTextField.getText(), wednesdayToTextField.getText()));
        }
        if(ValidatorModel.doctorEditDayValidator(thursdayBox.isSelected(),thursdayFromTextField.getText(),thursdayToTextField.getText())) {
            hours.put("thursday", new DoctorEditModel(thursdayBox.isSelected(), thursdayFromTextField.getText(), thursdayToTextField.getText()));
        }
        if(ValidatorModel.doctorEditDayValidator(fridayBox.isSelected(),fridayFromTextField.getText(),fridayToTextField.getText())) {
            hours.put("friday", new DoctorEditModel(fridayBox.isSelected(), fridayFromTextField.getText(), fridayToTextField.getText()));
        }
        if(ValidatorModel.intervalValidation(intervalTextField.getText())){
            System.out.println(intervalTextField.getText());
        }

        hours.forEach((k,v)-> System.out.println("day: " +k+" hour: "+v.getFrom()+" "+v.getTo()+" "+v.getActive()));

    }

    public void cancelButtonOnClick(ActionEvent event) throws IOException {
        //borderPane.setCenter(helpers.SwitchAnchor("DoctorReport", event));
        helpers.SwitchScene("DoctorMain", event);

    }

}
