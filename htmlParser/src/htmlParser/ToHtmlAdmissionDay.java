package htmlParser;

public class ToHtmlAdmissionDay {
    private String idDoctorWorkingDay, date;

    public ToHtmlAdmissionDay(String idDoctorWorkingDay, String date) {
        this.idDoctorWorkingDay = idDoctorWorkingDay;
        this.date = date;
    }

    public String getIdDoctorWorkingDay() {
        return idDoctorWorkingDay;
    }

    public void setIdDoctorWorkingDay(String idDoctorWorkingDay) {
        this.idDoctorWorkingDay = idDoctorWorkingDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
