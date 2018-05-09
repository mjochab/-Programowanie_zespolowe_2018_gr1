package pojo;

import java.sql.Time;

public class DoctorWorkingDays {
    private int doctorWorkingDayId;
    private int doctorId;
    private String day;
    private String hourFrom;
    private String hourTo;
    private String hourInterval;
    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getDoctorWorkingDayId() {
        return doctorWorkingDayId;
    }

    public void setDoctorWorkingDayId(int doctorWorkingDayId) {
        this.doctorWorkingDayId = doctorWorkingDayId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHourFrom() {
        return hourFrom;
    }

    public void setHourFrom(String hourFrom) {
        this.hourFrom = hourFrom;
    }

    public String getHourTo() {
        return hourTo;
    }

    public void setHourTo(String hourTo) {
        this.hourTo = hourTo;
    }

    public String getHourInterval() {
        return hourInterval;
    }

    public void setHourInterval(String hourInterval) {
        this.hourInterval = hourInterval;
    }

    public String doctorWorkingDaysToString() {
        return String.format("%s %s %s %s %s %s %s %s",
                "doctorWorkingDayId: " + getDoctorWorkingDayId(),
                "doctorId: " + getDoctorId(),
                "day: " + getDay(),
                "hourFrom: " + getHourFrom(),
                "hourTo: " + getHourTo(),
                "hourInterval: " + getHourInterval()
        );
    }
}
