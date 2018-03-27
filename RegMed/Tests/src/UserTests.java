import entities.Doctor;
import entities.Patient;
import org.junit.jupiter.api.*;
import repositories.RepositoryInterface;
import repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    @Test
    void user_repository_seed_will_return_4_users() {
        RepositoryInterface userRepository = new UserRepository();

        assertTrue(userRepository.getAll().size() == 4);
    }

    @Test
    void user_repository_seed_2_user_is_patient_3_doctor() {
        RepositoryInterface userRepository = new UserRepository();

        assertTrue(userRepository.get(1) instanceof Patient);
        assertTrue(userRepository.get(2) instanceof Doctor);
    }
}
