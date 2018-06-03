package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitAdministrationModel {

    private int id;
    private String doctor;
    private String specialization;
    private LocalTime hour;
    private LocalDate date;

    public VisitAdministrationModel(int id, String doctor, String specialization, LocalTime hour, LocalDate date) {
        this.id = id;
        this.doctor = doctor;
        this.specialization = specialization;
        this.hour = hour;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
