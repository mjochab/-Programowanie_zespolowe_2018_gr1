package pojo;

public class PatientsRefferal {
    private int refferalId;
    private int patientId;
    private int doctorId;
    private int specialistId;
    private String refferalforpatient;

    public int getRefferalId() {
        return refferalId;
    }

    public void setRefferalId(int refferalId) {
        this.refferalId = refferalId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public String getRefferalforpatient() {
        return refferalforpatient;
    }

    public void setRefferalforpatient(String refferalforpatient) {
        this.refferalforpatient = refferalforpatient;
    }
}
