package controllers.doctor;

import dto.DoctorModuleDTO;
import helpers.ControllerPagination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import models.DoctorEditModel;
import models.ValidatorModel;
import pojo.DoctorWorkingDays;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DoctorEditController implements ControllerPagination {


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

    @FXML
    DatePicker
            vadilityDatePicker;

    private DoctorModuleDTO doctorModuleDTO;
    List<DoctorWorkingDays> tableData;
    HashMap<String, DoctorWorkingDays> days = new HashMap<String, DoctorWorkingDays>();


    public DoctorEditController() {
        this.doctorModuleDTO = new DoctorModuleDTO();
        getDaysIfExist();
    }

    @FXML
    private void initialize() {
        fillHours();
    }

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

    public void okButtonOnClick(ActionEvent event) {
        HashMap<String, DoctorEditModel> hours = new HashMap<String, DoctorEditModel>();
        if (ValidatorModel.doctorEditDayValidator(mondayBox.isSelected(), mondayFromTextField.getText(), mondayToTextField.getText())) {
            hours.put("monday", new DoctorEditModel(mondayBox.isSelected(), mondayFromTextField.getText(), mondayToTextField.getText()));
        }
        if (ValidatorModel.doctorEditDayValidator(tuesdayBox.isSelected(), tuesdayFromTextField.getText(), tuesdayToTextField.getText())) {
            hours.put("tuesday", new DoctorEditModel(tuesdayBox.isSelected(), tuesdayFromTextField.getText(), tuesdayToTextField.getText()));
        }
        if (ValidatorModel.doctorEditDayValidator(wednesdayBox.isSelected(), wednesdayFromTextField.getText(), wednesdayToTextField.getText())) {
            hours.put("wednesday", new DoctorEditModel(wednesdayBox.isSelected(), wednesdayFromTextField.getText(), wednesdayToTextField.getText()));
        }
        if (ValidatorModel.doctorEditDayValidator(thursdayBox.isSelected(), thursdayFromTextField.getText(), thursdayToTextField.getText())) {
            hours.put("thursday", new DoctorEditModel(thursdayBox.isSelected(), thursdayFromTextField.getText(), thursdayToTextField.getText()));
        }
        if (ValidatorModel.doctorEditDayValidator(fridayBox.isSelected(), fridayFromTextField.getText(), fridayToTextField.getText())) {
            hours.put("friday", new DoctorEditModel(fridayBox.isSelected(), fridayFromTextField.getText(), fridayToTextField.getText()));
        }

        hours.forEach((k, v) -> insertWorkingDays(k, v.getFrom(), v.getTo(), intervalTextField.getText(), vadilityDatePicker.getValue().toString()));


    }

    public void insertWorkingDays(String day, String hourFrom, String hourTo, String hourInterval, String validateDay) {
        DoctorWorkingDays dayToAdd = new DoctorWorkingDays();
        dayToAdd.setId(12);
        dayToAdd.setDay(day);
        dayToAdd.setHourFrom(hourFrom);
        dayToAdd.setHourTo(hourTo);
        dayToAdd.setHourInterval(hourInterval);
        dayToAdd.setValidateDate(validateDay);
        doctorModuleDTO.add(dayToAdd);
    }

    public void getDaysIfExist() {
        days = doctorModuleDTO.getDoctorWorkingDays(12);
    }

    public void fillHours() {
        if (days.get("monday") != null) {
            mondayBox.setSelected(true);
            mondayFromTextField.setDisable(false);
            mondayToTextField.setDisable(false);
            mondayFromTextField.setText(days.get("monday").getHourFrom());
            mondayToTextField.setText(days.get("monday").getHourTo());
            intervalTextField.setText(days.get("monday").getHourInterval());
            vadilityDatePicker.setValue(LocalDate.parse(days.get("monday").getValidateDate()));
        }
        if (days.get("tuesday") != null) {
            tuesdayBox.setSelected(true);
            tuesdayFromTextField.setDisable(false);
            tuesdayToTextField.setDisable(false);
            tuesdayFromTextField.setText(days.get("tuesday").getHourFrom());
            tuesdayToTextField.setText(days.get("tuesday").getHourTo());
            intervalTextField.setText(days.get("tuesday").getHourInterval());
            vadilityDatePicker.setValue(LocalDate.parse(days.get("tuesday").getValidateDate()));
        }
        if (days.get("wednesday") != null) {
            wednesdayBox.setSelected(true);
            wednesdayFromTextField.setDisable(false);
            wednesdayToTextField.setDisable(false);
            wednesdayFromTextField.setText(days.get("wednesday").getHourFrom());
            wednesdayToTextField.setText(days.get("wednesday").getHourTo());
            intervalTextField.setText(days.get("wednesday").getHourInterval());
            vadilityDatePicker.setValue(LocalDate.parse(days.get("wednesday").getValidateDate()));
        }
        if (days.get("thursday") != null) {
            thursdayBox.setSelected(true);
            thursdayFromTextField.setDisable(false);
            thursdayToTextField.setDisable(false);
            thursdayFromTextField.setText(days.get("thursday").getHourFrom());
            thursdayToTextField.setText(days.get("thursday").getHourTo());
            intervalTextField.setText(days.get("thursday").getHourInterval());
            vadilityDatePicker.setValue(LocalDate.parse(days.get("thursday").getValidateDate()));
        }
        if (days.get("friday") != null) {
            fridayBox.setSelected(true);
            fridayFromTextField.setDisable(false);
            fridayToTextField.setDisable(false);
            fridayFromTextField.setText(days.get("friday").getHourFrom());
            fridayToTextField.setText(days.get("friday").getHourTo());
            intervalTextField.setText(days.get("friday").getHourInterval());
            vadilityDatePicker.setValue(LocalDate.parse(days.get("friday").getValidateDate()));
        }
    }

    public void cancelButtonOnClick(ActionEvent event) throws IOException {
        //borderPane.setCenter(helpers.SwitchAnchor("DoctorReport", event));
        helpers.SwitchScene("DoctorMain", event);

    }
}
