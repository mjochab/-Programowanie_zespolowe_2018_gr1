package pojo;

public class PatientsHistory {
    private int fileId;
    private int doctorId;
    private int patientId;
    private String recognition;
    private String showHistory;


    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }



    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getShowHistory() {
        return showHistory;
    }

    public void setShowHistory(String showHistory) {
        this.showHistory = showHistory;
    }
}
