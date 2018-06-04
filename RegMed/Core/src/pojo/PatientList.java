package pojo;

import java.time.LocalTime;

public class PatientList {
    private int id;
    private Patient patient;
    private AdmissionDay admissionDay;
    private LocalTime visitHour;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AdmissionDay getAdmissionDay() {
        return admissionDay;
    }

    public void setAdmissionDay(AdmissionDay admissionDay) {
        this.admissionDay = admissionDay;
    }

    public LocalTime getVisitHour() {
        return visitHour;
    }

    public void setVisitHour(LocalTime visitHour) {
        this.visitHour = visitHour;
    }
}
