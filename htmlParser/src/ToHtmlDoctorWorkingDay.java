public class ToHtmlDoctorWorkingDay {
    private String idDoctor, day, hourFrom, hourTo, hourInterval, validateDate;

    public ToHtmlDoctorWorkingDay(String idDoctor, String day, String hourFrom, String hourTo, String hourInterval, String validateDate) {
        this.idDoctor = idDoctor;
        this.day = day;
        this.hourFrom = hourFrom;
        this.hourTo = hourTo;
        this.hourInterval = hourInterval;
        this.validateDate = validateDate;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
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

    public String getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }
}
