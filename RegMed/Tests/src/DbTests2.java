import database.MyBatisDbConnection;
import dto.AdminAdministrationDTO;
import mappers.AdminAdministrationMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pojo.Administrator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbTests2 extends DatabaseTestingAbstractClass {

    AdminAdministrationDTO dto = new AdminAdministrationDTO();

    public DbTests2() throws NoSuchFieldException, IllegalAccessException {
        Field field = AdminAdministrationDTO.class.getDeclaredField("dbConnection");
        field.setAccessible(true);
        MyBatisDbConnection<AdminAdministrationMapper> a =
                new MyBatisDbConnection<>(AdminAdministrationMapper.class, "SqlMapConfig.xml");
        field.set(dto, a);
    }


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

