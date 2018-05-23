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
import pojo.AdmissionDay;
import pojo.Doctor;
import pojo.DoctorWorkingDays;
import pojo.SingleVisit;

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
    private ObservableList<Doctor> doctorsList;

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
    private ChoiceBox<String> doctorChoiceBox;



    public RegistrationController() {
        patientModuleDTO = new PatientModuleDTO();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDoctorWorkingDaysTableDataConnection();
        doctorsList = FXCollections.observableArrayList(patientModuleDTO.getAllDoctors());
        doctorChoiceBox.setItems(doctorsToString(doctorsList));

        setDatiePickerFields();

    }

    /**
     * Enabling in doctor admission days calendar only days, when doctor is admitting.
     */
    private void setDatiePickerFields() {
        //https://stackoverflow.com/questions/42542312/javafx-datepicker-color-single-cell
        List<AdmissionDay> admissionDays = new ArrayList<>(patientModuleDTO.getAllAdmissionDays());

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        setDisable(true);   //All not admission days are disabled

                        //all admission days are enabled
                        for (AdmissionDay admissionDay : admissionDays) {

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
     * TODO: incomplete
     */
    private void setDoctorWorkingDaysTableDataConnection() {
        dayColumn.setCellValueFactory(new PropertyValueFactory<DoctorWorkingDays, String>("day"));
        admissionHoursFromColumn.setCellValueFactory(new PropertyValueFactory<DoctorWorkingDays, String>("hourFrom"));
        admissionHoursToColumn.setCellValueFactory(new PropertyValueFactory<DoctorWorkingDays, String>("hourTo"));
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
     * Loading visits (free and busy) form day selected in calendar.
     */
    private void loadVisits() {
        AdmissionDay admissionDay = patientModuleDTO.getAdmissionDayByDate(getDateFromCalendar());
        ArrayList list = new ArrayList(parseVisitsToHour(patientModuleDTO.getAllVisits(admissionDay)));

        visitHourPicker.setItems(FXCollections.observableArrayList(list));
        hoursList.setItems(FXCollections.observableArrayList(list));


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
            list.add(visit.visitHourToString());
        }

        return list;
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
