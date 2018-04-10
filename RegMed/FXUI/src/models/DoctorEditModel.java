package models;

public class DoctorEditModel {
    private Boolean active;
    private String from;
    private String to;

    public DoctorEditModel(Boolean active, String from, String to) {
        this.active = active;
        this.from = from;
        this.to = to;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
