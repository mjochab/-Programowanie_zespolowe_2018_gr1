import entities.Patient;
import org.junit.jupiter.api.Test;
import repositories.PatientRepository;

import static org.junit.jupiter.api.Assertions.*;

public class PatientEntityTests {

    @Test
    void pesel_test() {
        Patient patient = new Patient(5, "Forename", "Name", "password", "123456897", "Brzozow");

        assertFalse(patient.getPesel().isEmpty());
    }
}
