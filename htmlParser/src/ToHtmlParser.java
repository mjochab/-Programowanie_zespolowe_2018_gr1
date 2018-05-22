import java.util.HashMap;
import java.util.Iterator;

public class ToHtmlParser {

    /**
     * @param patientData
     * @param patientHistory - 1st string -> date of visit, 2nd -> patient history write while that visit
     */
    public String patietFile(String[] patientData, HashMap<String, String> patientHistory) throws WrongDataException {
        String result = "";

        if (patientData == null || patientData.length != 10) {
            throw new WrongDataException("Wrong patient input data");
        }
        if (patientHistory.isEmpty() || patientHistory == null) {
            throw new WrongDataException("Wrong patient history data");
        }
        result += createPatientDataHtml(patientData) + createPatientHistoryHtml(patientHistory);

        return result;
    }


    /**
     *
     * @param patientData
     * @return
     */
    private String createPatientDataHtml(String[] patientData) {
        String result;

        String h1 = String.format("<h1>%s %s</h1>", patientData[0], patientData[1]),
                h2 = String.format("<h2>%s</h2>", patientData[2]),
                h3 = String.format("<h3>%s %s <br>%s %s</h3>", patientData[3], patientData[4], patientData[5], patientData[6]),
                h4 = String.format("<h4>%s</h4>", patientData[7]),
                h5 = String.format("<h5>%s %s</h5><br><hr>", patientData[8], patientData[9]);
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

    private String createDoctorPrescriptionHtml(ToHtmlDoctor doctor){

        String row1 = String.format("<h3>%s %s</h3>", doctor.getFirstName(), doctor.getLastName()),
                row2 = String.format("<h3>%s</h3>", doctor.getSpecialization()),
                row3 = String.format("<h3>%s</h3>", doctor.getPhoneNumber());
        return row1 + row2 + row3;
    }

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

    public String refference(ToHtmlDoctor doctor, ToHtmlDoctor doctorSpecialist, ToHtmlPatient patient, String content, String date){
        String result = "";
        result += String.format("<h2>Refferal to: %s</h2>", doctorSpecialist.getSpecialization());
        result += createPatientPerscriptionHtml(patient);
        result += String.format("<p>%s</p>",content);
        result += String.format("<h3>%s %s</h3>",doctor.getFirstName(), doctor.getLastName());
        return result;
    }

    public void test(){
        System.out.println("test");
    }
}
