import entities.Doctor;
import entities.Patient;
import org.junit.jupiter.api.*;
import repositories.PatientRepository;
import repositories.RepositoryInterface;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTests {


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
        assertTrue(patientRepository.get(4).getName() == "Name");
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
    void update_repo_test() {
        PatientRepository patientRepository = new PatientRepository();
        Patient patient = new Patient(5, "Forename", "Name", "password", "123456897", "Brzozow");
        patientRepository.add(patient);

        Patient patientExpected = patient;
        patientExpected.setName("Name2");
        patientExpected.setPesel("82398273291");
        patientRepository.update(patientExpected);

        Patient patientInRepo = patientRepository.get(patientRepository.getAll().size()-1);

        assertTrue(patientInRepo.getName() == patientExpected.getName());
        assertTrue(patientInRepo.getPesel() == patientExpected.getPesel());
        //assertTrue(patient.getName() != patientInRepo.getName());

    }
}
