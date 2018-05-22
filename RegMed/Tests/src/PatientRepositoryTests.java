import dto.FileDTO;
import entities.Doctor;
import entities.Patient;
import org.junit.jupiter.api.*;
import pojo.File;
import repositories.PatientRepository;
import repositories.RepositoryInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientRepositoryTests {


    @Test
    void getId_returning_last_index() {
        PatientRepository patientRepository = new PatientRepository();

        List<Patient> allPatients = new ArrayList<Patient>(patientRepository.getAll());

        assertTrue(patientRepository.getMaxId() == allPatients.size());
    }

    @Test
    void getId_returning_last_index_with_2_added_patients() {
        PatientRepository patientRepository = new PatientRepository();

        Patient p1 = new Patient(patientRepository.getMaxId(), "Forename", "Name", "password", "123456897", "Brzozow");
    }

    @Test
    void add_patient_test() {
        PatientRepository patientRepository = new PatientRepository();
        Patient patient = new Patient(5, "Forename", "Name", "password", "123456897", "Brzozow");
        patientRepository.add(patient);

        assertTrue(patientRepository.getAll().size() == 5);
        assertTrue(patientRepository.get(5).getName() == "Name");
    }

    @Test
    void add_patient_id_iteration_test() {
        PatientRepository patientRepository = new PatientRepository();
        int patientRepoPrevMaxId = patientRepository.getMaxId();

        Patient patientToAdd = new Patient(patientRepository.getNewMaxId(), "Forename", "Name", "password", "123456897", "Brzozow");
        patientRepository.add(patientToAdd);

        int patientRepoNewMaxId = patientRepository.getMaxId();

        assertTrue(patientRepoPrevMaxId != patientRepoNewMaxId);
        assertTrue(patientRepoPrevMaxId == 4);
        assertTrue(patientRepoNewMaxId == 5);
    }

    @Test
    @DisplayName("after_update_patient_will_have_different_name_and_pesel")
    void update_repo_test() {
        PatientRepository patientRepository = new PatientRepository();
        Patient patient = new Patient(patientRepository.getNewMaxId(), "Forename", "Name", "password", "123456897", "Brzozow");
        patientRepository.add(patient);

        Patient patientBefore = new Patient(patient);
        Patient patientExpected = patient;
        patientExpected.setName("DifferentName");
        patientExpected.setPesel("82398273291");
        patientRepository.update(patientExpected);

        Patient patientInRepo = patientRepository.get(patientRepository.getAll().size());

        assertTrue(patientInRepo.getName() == patientExpected.getName());
        assertTrue(patientInRepo.getPesel() == patientExpected.getPesel());
        assertFalse(patientBefore.getName() == patientInRepo.getName());
    }

    @Test
    void add_to_cleared_repo_is_possible() {
        RepositoryInterface patientRepository = new PatientRepository();

        Patient patientToAdd = new Patient(patientRepository.getNewMaxId(), "Martyna", "Porodowicz",
                "password", "95829484732", "Brzozów");

        patientRepository.remove(patientRepository.get(1));
        patientRepository.remove(patientRepository.get(2));
        patientRepository.remove(patientRepository.get(4));
        patientRepository.remove(patientRepository.get(3));
        patientRepository.add(patientToAdd);

        assertTrue(patientRepository.getAll().size() == 1);

    }

    @Test
    void test(){
        FileDTO file = new FileDTO();
        ArrayList files = new ArrayList(file.getFiles(1));
        ToHtmlParser parser = new ToHtmlParser();
        ToHtmlDoctor doctor = new ToHtmlDoctor(file.getFiles(1).get(0).getDoctor().getFirstName(), file.getFiles(1).get(0).getDoctor().getLastName());
        ToHtmlPatient patient = new ToHtmlPatient(file.getFiles(1).get(0).getPatient().getFirstName(),
                file.getFiles(1).get(0).getPatient().getLastName(),
                file.getFiles(1).get(0).getPatient().getPesel(),
                file.getFiles(1).get(0).getPatient().getAddress().getZip(),
                file.getFiles(1).get(0).getPatient().getAddress().getCity(),
                file.getFiles(1).get(0).getPatient().getAddress().getStreet(),
                String.valueOf(file.getFiles(1).get(0).getPatient().getAddress().getNumber()),
                file.getFiles(1).get(0).getPatient().getPhoneNumber());
        String date = file.getFiles(1).get(0).getDate();
        String history = file.getFiles(1).get(0).getHistory();
        HashMap<String, String> h = new HashMap<>();
        h.put(date,history);
        System.out.println(parser.patietFile(patient,doctor,h));


    }

}
