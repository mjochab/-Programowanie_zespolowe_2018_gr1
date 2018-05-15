import dto.AdminAdministrationDTO;
import org.junit.jupiter.api.Test;
import pojo.Administrator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbTests2 extends DatabaseTestingAbstractClass {

    AdminAdministrationDTO dto = new AdminAdministrationDTO();
    @Test
    void test() {
        dto.delete(1);

        List<Administrator> result = new ArrayList<>(dto.getAll());
        assertTrue(result.size() == 0);
    }

    @Test
    void test2() {
        Administrator result = dto.get(1);

        assertEquals("Jan", result.getFirstName());
        assertEquals("Kowalski", result.getLastName());
    }

}
