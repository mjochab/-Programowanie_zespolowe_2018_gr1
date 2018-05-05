package dbTests.dtoTests;

import dto.AdminAdministrationDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pojo.Administrator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AdminAdministrationDTOTests {

    AdminAdministrationDTO adminAdministrationDTO
            = new AdminAdministrationDTO();

    @Test
    void getAll_test() {
        ArrayList<Administrator> result = adminAdministrationDTO.getAll();

        for (Administrator admin : result) {
            System.out.println(admin.administratorToString());
        }
    }

    @Test
    void get_test() {
        Administrator result = adminAdministrationDTO.get(1);
        System.out.println(result.administratorToString());
    }

    @Disabled
    @Test
    void add_test() {
        Administrator adminToAdd = new Administrator();
        adminToAdd.setAdminId(0);
        adminToAdd.setFirstName("admin");
        adminToAdd.setLastName("adminLastName");
        adminToAdd.setPesel("12345678910");
        adminToAdd.setEmail("admin@admin.com");
        adminToAdd.setPhoneNumber("999888777");
        adminToAdd.setPassword(adminToAdd.getLastName());

        adminAdministrationDTO.add(adminToAdd);
    }


    @Disabled
    @Test
    void update_test() {
        Administrator result = adminAdministrationDTO.get(2);
        String value = new SimpleDateFormat("HH:mm:ss")
                .format(Calendar.getInstance().getTime());
        result.setFirstName(value);
        result.setLastName(value);
        result.setPesel(value);
        result.setEmail(value);
        result.setPhoneNumber(value);

        adminAdministrationDTO.update(result);

        System.out.println(adminAdministrationDTO.get(2).administratorToString());
    }


    @Disabled
    @Test
    void delete_test() {
        adminAdministrationDTO.delete(3);
    }

}
