package pojo;

import java.sql.Time;
import java.time.LocalTime;

public class SingleVisit {
    int id;
    LocalTime visitHour;
    Patient patient;
    AdmissionDay admissionDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getVisitHour() {
        return visitHour;
    }

    public void setVisitHour(LocalTime visitHour) {
        this.visitHour = visitHour;
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


    public String visitHourToString() {
        return String.format("%d:%d", visitHour.getHour(), visitHour.getMinute());
    }


}
