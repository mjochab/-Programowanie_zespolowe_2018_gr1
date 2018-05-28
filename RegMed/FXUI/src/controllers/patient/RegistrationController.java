package controllers.patient;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import dto.PatientModuleDTO;
import helpers.ControllerPagination;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import pojo.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Allowing select visit at selected first contact doctor or specialist.
 *
 * @author Pawel Lawera
 */
public class RegistrationController implements Initializable, ControllerPagination {

    private PatientModuleDTO patientModuleDTO;
    private ObservableList<DoctorWorkingDays> doctorWorkingDaysTableData;
    private ObservableList<String> specializationsList;

    @FXML
    private DatePicker visitDatePicker;

    @FXML
    private ChoiceBox<SingleVisit> visitHourPicker;

    @FXML
    private ListView<String> hoursList;

    @FXML
    private TableView<DoctorWorkingDays> doctorWorkingDaysTable;

    @FXML
    private TableColumn<DoctorWorkingDays, String> dayColumn,
        admissionHoursFromColumn,
        admissionHoursToColumn;

    @FXML
    private ChoiceBox<String> doctorChoiceBox,
            specializationChoiceBox;



    public RegistrationController() {
        patientModuleDTO = new PatientModuleDTO();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDoctorWorkingDaysTableDataConnection();
        setDatiePickerFields();
        loadDataIntoSpecializationAndDoctorsChoiceBoxes();
    }

    /**
     * TODO: incomplete
     */
    private void setDoctorWorkingDaysTableDataConnection() {
        dayColumn.setCellValueFactory(new PropertyValueFactory<DoctorWorkingDays, String>("day"));
        admissionHoursFromColumn.setCellValueFactory(new PropertyValueFactory<DoctorWorkingDays, String>("hourFrom"));
        admissionHoursToColumn.setCellValueFactory(new PropertyValueFactory<DoctorWorkingDays, String>("hourTo"));
    }

    /**
     * Loading data into specializations choice box, and doctors connected with it
     * into doctors choice box.
     */
    private void loadDataIntoSpecializationAndDoctorsChoiceBoxes() {
        specializationsList = FXCollections.observableArrayList(patientModuleDTO.getSpecializationsNames());
        specializationChoiceBox.setItems(specializationsList);

        specializationChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            ObservableList<Doctor> doctorsList = FXCollections.observableArrayList(
                patientModuleDTO.getDoctorsBySpecialization(specializationChoiceBox.getValue()));
            doctorChoiceBox.setItems(doctorsToString(doctorsList));

        });


    }


    /**
     * After doctors choice box clicked setting setted doctor admission days
     * in tableView.
     */
    @FXML
    private void doctorsChoiceBoxClicked() {

        List<Doctor> d = new ArrayList<>(patientModuleDTO.getDoctorsBySpecialization(
                specializationChoiceBox.getValue()));
        doctorChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            try {
                loadDoctorWorkingDaysDataToTable(d.get(doctorChoiceBox.getSelectionModel().getSelectedIndex()).getId());
            } catch (IndexOutOfBoundsException ex) {
                System.err.print("Doctor havnt addmission days\n");
                doctorWorkingDaysTable.setPlaceholder(new Label("Doctor have not admission days."));
            }

        });

    }

    /**
     * Loading doctor working days into table.
     *
     * @param doctorId doctor id of which working days you want load to table
     *                 with admission days.
     */
    private void loadDoctorWorkingDaysDataToTable(int doctorId) {
        doctorWorkingDaysTableData = FXCollections.observableArrayList(patientModuleDTO.getDoctorWorkingDays(doctorId));
        doctorWorkingDaysTable.setItems(doctorWorkingDaysTableData);
    }


    /**
     * Enabling in doctor admission days calendar only days, when doctor is admitting.
     */
    private void setDatiePickerFields() {
        //https://stackoverflow.com/questions/42542312/javafx-datepicker-color-single-cell
        //List<LocalDate> admissionDays = new ArrayList<>(patientModuleDTO.getAdmissionDaysDatesForDoctor(12));   //TODO: just get from selected doctor.
        List<AdmissionDay2> admissionDays = new ArrayList<>(patientModuleDTO.getAdmissionDaysForDoctor(12));
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        setDisable(true);   //All not admission days are disabled

                        //all admission days are enabled
                        for (AdmissionDay2 admissionDay : admissionDays) {

                            if (MonthDay.from(item).equals(MonthDay.of(admissionDay.getDate().getMonth(),
                                    admissionDay.getDate().getDayOfMonth()))) {
                                setDisable(false);

                            }
                        }

                        //Dorobic sprawdzanie czy dzien jest juz pelny
                    }
                };
            }
        };

        visitDatePicker.setDayCellFactory(dayCellFactory);
    }


    /**
     * Switching scene back to admin default screen after login.
     *
     * @param event         using by pagination helper for get current scene.
     *                      It is necessary to switch from one scene to another.
     * @see helpers.ControllerHelpers
     * @throws IOException  throwing when fxml file wasn't found
     */
    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        helpers.SwitchScene("patient/PatientHome", event);
    }


    /**
     * Inserting visits (free and busy) form day selected in calendar.
     */
    @FXML
    private void findButtonClicked() {
        loadVisits();
    }


    /**
     * Loading visits (free and busy) form day selected in calendar.
     */
    private void loadVisits() {

        List<SingleVisit> visitsInDb = new ArrayList<>(patientModuleDTO.getSingleVisitsFromDate(visitDatePicker.getValue(), 12));

        visitHourPicker.setItems(FXCollections.observableArrayList(visitsInDb));
        //hoursList.setItems(FXCollections.observableArrayList(parseVisitsToHour(visitsInDb)));


        AdmissionDay2 admissionDay = patientModuleDTO.getAdmissionDayForVisitPicker(visitDatePicker.getValue());
        List<SingleVisit> allVisits = new ArrayList<>(patientModuleDTO.getAllVisits(admissionDay));

        hoursList.setItems(FXCollections.observableArrayList(parseVisitsToHour(allVisits)));
    }





    /**
     * Converting list with doctor objects to strings, containg doctors
     * first and last name.
     *
     * @param list of doctors
     * @return  list of strings, containing doctors first and last names.
     */
    private ObservableList<String> doctorsToString(ObservableList<Doctor> list) {
        List<String> listString = new ArrayList<>();
        for (Doctor doc : list) {
            String value = doc.getFirstName() + " " + doc.getLastName();
            listString.add(value);
        }
        return FXCollections.observableArrayList(listString);
    }


    /**
     * Converting list with visits as hours to string.
     * @param visits    list of visits hours in datetime.
     * @return          list of visits hours in string list.
     */
    private List<String> parseVisitsToHour(List<SingleVisit> visits) {
        List<String> list = new ArrayList<>();

        for (SingleVisit visit : visits) {
            list.add(parseVisitToAvailabilityInformation(visit));
        }

        return list;
    }

    private String parseVisitToAvailabilityInformation(SingleVisit singleVisit) {
        if (singleVisit.getPatient() != null) {
            return String.format("%d:%d  -  Term is not free", singleVisit.getVisitHour().getHour(), singleVisit.getVisitHour().getMinute());
        } else {
            return String.format("%d:%d  -  Term is free", singleVisit.getVisitHour().getHour(), singleVisit.getVisitHour().getMinute());
        }
    }

    /**
     * Getting date from selected cell in callendar.
     *
     * @return date of selected cell.
     */
    private LocalDate getDateFromCalendar() {
        return visitDatePicker.getValue();
    }


}
