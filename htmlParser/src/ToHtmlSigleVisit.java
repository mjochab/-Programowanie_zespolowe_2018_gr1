public class ToHtmlSigleVisit {
    private String idAdmissionDay, visitHour, idPatient;

    public ToHtmlSigleVisit(String idAdmissionDay, String visitHour, String idPatient) {
        this.idAdmissionDay = idAdmissionDay;
        this.visitHour = visitHour;
        this.idPatient = idPatient;
    }

    public String getIdAdmissionDay() {
        return idAdmissionDay;
    }

    public void setIdAdmissionDay(String idAdmissionDay) {
        this.idAdmissionDay = idAdmissionDay;
    }

    public String getVisitHour() {
        return visitHour;
    }

    public void setVisitHour(String visitHour) {
        this.visitHour = visitHour;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }
}
