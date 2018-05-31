package controllers.patient;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import dto.PatientModuleDTO;
import helpers.ControllerPagination;
import helpers.DialogBox;
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
import org.apache.ibatis.exceptions.PersistenceException;
import pojo.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    private static Patient patient;
    private int selectedDoctorId;

    private PatientModuleDTO patientModuleDTO;
    private ObservableList<DoctorWorkingDays> doctorWorkingDaysTableData;
    private ObservableList<String> specializationsList;

    @FXML
    private DatePicker visitDatePicker;


    @FXML
    private ListView<SingleVisit> hoursList;

    @FXML
    private TableView<DoctorWorkingDays> doctorWorkingDaysTable;

    @FXML
    private TableColumn<DoctorWorkingDays, String> dayColumn,
        admissionHoursFromColumn,
        admissionHoursToColumn;

    @FXML
    private ChoiceBox<String> doctorChoiceBox,
            specializationChoiceBox,
            termChoiceBox;

    @FXML
    private Button selectVisitButton,
            confirmChoiceVisitButton,
            confirmFirstPossibleTermButton;

    @FXML
    private Label termLabel;


    public RegistrationController() {
        patientModuleDTO = new PatientModuleDTO();
        patient = patientModuleDTO.get(1);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDoctorWorkingDaysTableDataConnection();

        loadDataIntoSpecializationAndDoctorsChoiceBoxes();
    }


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
                hoursList.getItems().clear();
                loadDoctorWorkingDaysDataToTable(d.get(doctorChoiceBox.getSelectionModel().getSelectedIndex()).getId());
                selectedDoctorId = d.get(doctorChoiceBox.getSelectionModel().getSelectedIndex()).getId();
                setDatiePickerFields();
            } catch (IndexOutOfBoundsException ex) {
                System.err.print("Doctor havnt addmission days\n");
                doctorWorkingDaysTable.setPlaceholder(new Label("Doctor have not admission days."));
            }

            setFirstPossibleTerm();

        });

    }

    private void setFirstPossibleTerm() {
        List<SingleVisit> listOfFreeVisits = new ArrayList<>(
                patientModuleDTO.getFirst10FreeSingleVisits(selectedDoctorId));

        termLabel.setText(String.format("%s, at: %s",
                listOfFreeVisits.get(0).getAdmissionDay2().getDate(),
                listOfFreeVisits.get(0).getVisitHour()));

        termChoiceBox.setItems(FXCollections.observableArrayList(parseTermsToStringChoiceBox(listOfFreeVisits)));

        confirmChoiceVisitButton.setOnAction(e -> {
            int selectedIndex = termChoiceBox.getSelectionModel().getSelectedIndex();
            SingleVisit selectedVisit = listOfFreeVisits.get(selectedIndex);
            selectedVisit.setPatient(patient);
            try {
                patientModuleDTO.addSingleVisit(selectedVisit);
                DialogBox.informationBox("Success", String.format("Successfully registered for the visit:\n\n %s %s, %s, %s.",
                        selectedVisit.getAdmissionDay2().getDoctor().getFirstName(),
                        selectedVisit.getAdmissionDay2().getDoctor().getLastName(),
                        selectedVisit.getAdmissionDay2().getDate(),
                        selectedVisit.getVisitHour()));
            } catch (Exception ex) {
                System.err.print(ex.getMessage());
            }
        });

        confirmFirstPossibleTermButton.setOnAction(e -> {
            SingleVisit visitToAdd = listOfFreeVisits.get(0);
            visitToAdd.setPatient(patient);
            try {
                patientModuleDTO.addSingleVisit(visitToAdd);
                DialogBox.informationBox("Success", String.format("Successfully registered for the visit:\n\n %s %s, %s, %s.",
                        visitToAdd.getAdmissionDay2().getDoctor().getFirstName(),
                        visitToAdd.getAdmissionDay2().getDoctor().getLastName(),
                        visitToAdd.getAdmissionDay2().getDate(),
                        visitToAdd.getVisitHour()));
            } catch (Exception ex) {
                System.err.print(ex.getMessage());
            }
        });



    }

    private List<String> parseTermsToStringChoiceBox(List<SingleVisit> singleVisits) {
        List<String> result = new ArrayList<>();

        for(SingleVisit visit : singleVisits) {
            result.add(String.format("%s, at: %s",
                    visit.getAdmissionDay2().getDate(),
                    visit.getVisitHour()
            )) ;
        }

        return result;
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

        List<AdmissionDay2> admissionDays = new ArrayList<>(patientModuleDTO.getAdmissionDaysForDoctor(selectedDoctorId));

        LocalDate mthCurrent = LocalDate.now();
        LocalDate mthAfter = mthCurrent.plusMonths(1);
        List<AdmissionDay2> list = new ArrayList<>(patientModuleDTO.admissionDaysBetweenDates(mthCurrent, mthAfter, selectedDoctorId));
        List<AdmissionDay2> list2 = new ArrayList<>(patientModuleDTO.admissionDaysFullOfVisits(list, selectedDoctorId));


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
                                    admissionDay.getDate().getDayOfMonth())) ){
                                    //&& dateIsAfterToday(admissionDay) ) { //checking if date is after today
                                setDisable(false);
                            }
                        }

                        for (AdmissionDay2 admissionDay : list2) {
                            if (MonthDay.from(item).equals(MonthDay.of(admissionDay.getDate().getMonth(),
                                    admissionDay.getDate().getDayOfMonth()))) {
                                setStyle("-fx-background-color: #ff4444;");
                                setTooltip(new Tooltip("All visits booked!"));
                            }
                        }

                    }
                };
            }
        };

        visitDatePicker.setDayCellFactory(dayCellFactory);

        //Showing visits from selected day
        visitDatePicker.setOnAction(e -> {
            loadVisits();
            selectVisitButton.setVisible(true);
        });
    }

    private boolean dateIsAfterToday(AdmissionDay2 admissionDay) {
        LocalDate today = LocalDate.now();
        if (today.compareTo(admissionDay.getDate()) <= 0) {
            return true;
        }
        return false;
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
     * Loading visits (free and busy) form day selected in calendar.
     */
    private void loadVisits() {

        List<SingleVisit> visitsInDb = new ArrayList<>(patientModuleDTO.getSingleVisitsFromDate(visitDatePicker.getValue(), selectedDoctorId));

        //visitHourPicker.setItems(FXCollections.observableArrayList(visitsInDb));
        //hoursList.setItems(FXCollections.observableArrayList(parseVisitsToHour(visitsInDb)));


        AdmissionDay2 admissionDay = patientModuleDTO.getAdmissionDayForVisitPicker(visitDatePicker.getValue(), selectedDoctorId);

        List<SingleVisit> allVisits = new ArrayList<>(patientModuleDTO.getAllVisits(admissionDay));


        setVisitCellFactory();
        hoursList.setItems(FXCollections.observableArrayList(allVisits));

    }

    private void setVisitCellFactory() {
        hoursList.setCellFactory(param -> new ListCell<SingleVisit>() {
            @Override
            protected void updateItem(SingleVisit item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else if (item.getPatient() == null) {
                    setText(String.format("%d:%d - termin is free", item.getVisitHour().getHour(), item.getVisitHour().getMinute()));
                    setStyle("-fx-background-color: rgba(37,255,36,0.11);");
                } else {
                    setText(String.format("%d:%d - termin is booked", item.getVisitHour().getHour(), item.getVisitHour().getMinute()));
                    setStyle("-fx-background-color: rgba(255,26,14,0.11);");

                }
            }
        });
    }


    @FXML
    private void selectVisitButtonClicked() {
        try {
            SingleVisit selectedVisit = saveSelectedVisitToDatabase(getSelectedSingleVisitWithPatient(patient));
            selectVisitButton.setVisible(false);
            loadVisits();
            visitDatePicker.getEditor().clear();
            DialogBox.informationBox("Success", String.format("Successfully registered for the visit:\n\n %s %s, %s, %s.",
                    selectedVisit.getAdmissionDay2().getDoctor().getFirstName(),
                    selectedVisit.getAdmissionDay2().getDoctor().getLastName(),
                    selectedVisit.getAdmissionDay2().getDate(),
                    selectedVisit.getVisitHour()));
        } catch (PersistenceException e) {
            DialogBox.warningBox("Warning!", "This term is booked.");
        }
        catch (IndexOutOfBoundsException ex) {
            DialogBox.warningBox("Warning!", "Please select visit!");
        }
    }

    private SingleVisit saveSelectedVisitToDatabase(SingleVisit selectedVisit) {
            patientModuleDTO.addSingleVisit(selectedVisit);
            return selectedVisit;
    }

    private SingleVisit getSelectedSingleVisitWithPatient(Patient patient) {
        AdmissionDay2 admissionDay = patientModuleDTO.getAdmissionDayForVisitPicker(visitDatePicker.getValue(), selectedDoctorId);
        List<SingleVisit> allVisits = new ArrayList<>(patientModuleDTO.getAllVisits(admissionDay));

        int selectedIndex = hoursList.getSelectionModel().getSelectedIndex();
        SingleVisit result = allVisits.get(selectedIndex);

        result.setAdmissionDay2(admissionDay);
        result.setPatient(patient);

        return result;
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
     * Getting date from selected cell in callendar.
     *
     * @return date of selected cell.
     */
    private LocalDate getDateFromCalendar() {
        return visitDatePicker.getValue();
    }


}
