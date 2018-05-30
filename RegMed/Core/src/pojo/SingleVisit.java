package pojo;

import java.sql.Time;
import java.time.LocalTime;

public class SingleVisit {
    int id;
    LocalTime visitHour;
    Patient patient;
    AdmissionDay admissionDay;
    AdmissionDay2 admissionDay2;

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

    public AdmissionDay2 getAdmissionDay2() {
        return admissionDay2;
    }

    public void setAdmissionDay2(AdmissionDay2 admissionDay2) {
        this.admissionDay2 = admissionDay2;
    }

    public String visitHourToString() {
        //return String.format("%d:%d", visitHour.getHour(), visitHour.getMinute());
        if (patient != null) {
            return String.format("%d:%d  -  Term is not free", visitHour.getHour(), visitHour.getMinute());
        } else {
            return String.format("%d:%d  -  Term is free", visitHour.getHour(), visitHour.getMinute());
        }
    }


}
