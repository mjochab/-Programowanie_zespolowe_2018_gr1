package htmlParser;

import java.util.HashMap;
import java.util.Iterator;

public class ToHtmlParser {

    /**
     *
     * @param patient
     * @param doctor
     * @param patientHistory
     * @return
     */
    public String patietFile(ToHtmlPatient patient, ToHtmlDoctor doctor, HashMap<String, String> patientHistory){
        String result = "";
        result += createPatientDataHtml(patient, doctor) + createPatientHistoryHtml(patientHistory);

        return result;
    }


    /**
     *
     * @param patient
     * @param doctor
     * @return
     */
    private String createPatientDataHtml(ToHtmlPatient patient, ToHtmlDoctor doctor) {
        String result;

        String h1 = String.format("<h1>%s %s</h1>", patient.getFirstName(), patient.getLastName()),
                h2 = String.format("<h2>%s</h2>", patient.getPesel()),
                h3 = String.format("<h3>%s %s <br>%s %s</h3>", patient.getZipCode(), patient.getCity(), patient.getStreet(), patient.getNumber()),
                h4 = String.format("<h4>%s</h4>", patient.getPhoneNumber()),
                h5 = String.format("<h5>%s %s</h5><br><hr>", doctor.getFirstName(), doctor.getLastName());
        result = h1 + h2 + h3 + h4 + h5;

        return result;
    }


    /**
     *
     * @param patientHistory
     * @return
     */
    private String createPatientHistoryHtml(HashMap<String, String> patientHistory) {
        String result = null;

        Iterator it = patientHistory.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String h2 = String.format("<h2>%s</h2>", pair.getKey()),
                    p = String.format("<p>%s</p><br><hr>", pair.getValue());
            result += (h2 + p);
            it.remove();
        }

        return result;
    }

    /**
     *
     * @param doctor
     * @param patient
     * @param content
     * @param date
     * @return
     */
    public String prescription(ToHtmlDoctor doctor, ToHtmlPatient patient, String content, String date){
        String result = null;

        result += createDoctorPrescriptionHtml(doctor);
        result += "<hr><br>";
        result += createPatientPerscriptionHtml(patient);
        result += "<hr><br><p>" + content + "</p>";
        result += String.format("<h3>%s</h3>", date);
        result += createDoctorPrescriptionHtml(doctor);

        return result;
    }

    /**
     *
     * @param doctor
     * @return
     */
    private String createDoctorPrescriptionHtml(ToHtmlDoctor doctor){

        String row1 = String.format("<h3>%s %s</h3>", doctor.getFirstName(), doctor.getLastName()),
                row2 = String.format("<h3>%s</h3>", doctor.getSpecialization()),
                row3 = String.format("<h3>%s</h3>", doctor.getPhoneNumber());
        return row1 + row2 + row3;
    }

    /**
     *
     * @param patient
     * @return
     */
    private String createPatientPerscriptionHtml(ToHtmlPatient patient)
    {
        String row1 = String.format("<h3>%s %s</h3>", patient.getFirstName(), patient.getLastName()),
                row2 = String.format("<h3>%s %s, %s %s</h3>",
                        patient.getZipCode(),
                        patient.getCity(),
                        patient.getStreet(),
                        patient.getNumber()),
                row3 = String.format("<h3>%s</h3>", patient.getPesel());

        return row1 + row2 + row3;
    }

    /**
     *
     * @param doctor
     * @param doctorSpecialist
     * @param patient
     * @param content
     * @param date
     * @return
     */
    public String refference(ToHtmlDoctor doctor, ToHtmlDoctor doctorSpecialist, ToHtmlPatient patient, String content, String date){
        String result = "";
        result += String.format("<h2>Refferal to: %s</h2>", doctorSpecialist.getSpecialization());
        result += createPatientPerscriptionHtml(patient);
        result += String.format("<p>%s</p>",content);
        result += String.format("<h3>%s %s</h3>",doctor.getFirstName(), doctor.getLastName());
        return result;
    }

    /**
     *
     *
     * @param singleVisit
     * @param date
     * @return
     */

    public String dailyPatientList(HashMap<String, String> visitOfDay){
        String result="";
        result += createDailyPatientListHtml(visitOfDay);
        return result;
    }

    private String createDailyPatientListHtml(HashMap<String, String> visitOfDay){
        String result = "";

        Iterator it = visitOfDay.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String h2 = String.format("<h2>%s</h2>", pair.getKey()),
                    p = String.format("<h3>%s</h3><br><hr>", pair.getValue());
            result += (h2 + p);
            it.remove();
        }

        return result;
    }

    /**
     *
     */
    public void test(){
        System.out.println("test");
    }
}
