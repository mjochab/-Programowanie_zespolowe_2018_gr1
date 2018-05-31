package pojo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

public class AdmissionDay2 implements Comparator<AdmissionDay2> {
    private int id;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime hourFrom;
    private LocalTime hourTo;
    private int hourInterval;
    private LocalDate validateDate; //expiration date

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHourFrom() {
        return hourFrom;
    }

    public void setHourFrom(LocalTime hourFrom) {
        this.hourFrom = hourFrom;
    }

    public LocalTime getHourTo() {
        return hourTo;
    }

    public void setHourTo(LocalTime hourTo) {
        this.hourTo = hourTo;
    }

    public int getHourInterval() {
        return hourInterval;
    }

    public void setHourInterval(int hourInterval) {
        this.hourInterval = hourInterval;
    }

    public LocalDate getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(LocalDate validateDate) {
        this.validateDate = validateDate;
    }

    /**
     * Comparing two dates.
     *
     * @param o1 first date.
     * @param o2 second date.
     * @return  0 - dates are same.
     *          1 - higher date will be returned (ascending)
     *          -1 - smaller date will be returned (descending)
     */
    @Override
    public int compare(AdmissionDay2 o1, AdmissionDay2 o2) {
        if (o1.getDate().isEqual(o2.getDate())) return 0;
        if (o1.getDate().isBefore(o2.getDate())) {
            return 1;
        } else return -1;
    }
}
