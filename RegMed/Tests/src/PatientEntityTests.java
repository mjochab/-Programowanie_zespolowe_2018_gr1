import entities.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import repositories.PatientRepository;



public class PatientEntityTests {

    @Test
    void pesel_test() {
        Patient patient = new Patient(5, "Forename", "Name", "password", "123456897", "Brzozow");

        assertFalse(patient.getPesel().isEmpty());
    }
}
