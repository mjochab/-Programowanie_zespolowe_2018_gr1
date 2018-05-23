package pojo;

import java.time.LocalDate;
import java.util.Date;

public class AdmissionDay {
    int id;
    LocalDate date;
    DoctorWorkingDays doctorWorkingDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DoctorWorkingDays getDoctorWorkingDay() {
        return doctorWorkingDay;
    }

    public void setDoctorWorkingDay(DoctorWorkingDays doctorWorkingDay) {
        this.doctorWorkingDay = doctorWorkingDay;
    }
}
