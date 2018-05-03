package databaseTests;

import models.DoctorAdministrationDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pojo.Address;
import pojo.Doctor;

import java.util.ArrayList;

public class DoctorAdministrationDTOTests {

    DoctorAdministrationDTO doctorAdministrationDTO
            = new DoctorAdministrationDTO();

    @Test
    void getAll_test() {
        ArrayList<Doctor> result = doctorAdministrationDTO.getAll();

        for (Doctor doctor : result) {
            System.out.println(doctor.getAddress().toString());
        }

        assertTrue(result.size() == 3);
    }

    @Test
    void get_test() {
        Doctor result = doctorAdministrationDTO.get(2);

        assertEquals("78281732398", result.getPesel());
        assertEquals("Joanna", result.getFirstName());
    }

    @Disabled
    @Test
    void add_test() {
        Address address = new Address();
        address.setCity("Poznan");
        address.setZip("23-231");
        address.setStreet("Grzybowa");
        address.setNumber(23);

        Doctor doctor = new Doctor();
        doctor.setDoctorId(0);
        doctor.setFirstName("Alojzy");
        doctor.setLastName("Stasiewicz");
        doctor.setPesel("91821922813");
        doctor.setAddress(address);
        doctor.setEmail("alojzy@stasiewicz.com");
        doctor.setPhoneNumber("221883223");
        doctor.setSpecializationId(5);
        doctor.setPassword(doctor.getFirstName());

        doctorAdministrationDTO.add(doctor);

        assertEquals(3, doctorAdministrationDTO.getAll().size());
        System.out.println(doctorAdministrationDTO.get(3).doctorToString());
    }

    @Disabled
    @Test
    void update_doctor_test() {
        Doctor before = doctorAdministrationDTO.get(3);
        System.out.println(doctorAdministrationDTO.get(3).doctorToString());
        Doctor result = doctorAdministrationDTO.get(3);
        result.setFirstName("firstName");
        result.setLastName("lastName");
        result.setPesel("11111111111");
        result.setEmail("a@a.com");
        result.setPhoneNumber("111222333");

        doctorAdministrationDTO.update(result);

        System.out.println(doctorAdministrationDTO.get(3).doctorToString());
    }

    @Disabled
    @Test
    void update_doctor_address_test() {
        Address result = doctorAdministrationDTO.get(3).getAddress();
        System.out.println(result.toString());

        result.setCity("cityy");
        result.setZip("11-112");
        result.setStreet("streett");
        result.setNumber(1234);

        doctorAdministrationDTO.updateAddress(result);
        System.out.println(result.toString());
    }

    @Disabled
    @Test
    void update_doctor_specializationId() {
        Doctor result = doctorAdministrationDTO.get(3);

        doctorAdministrationDTO.updateSpecialization(result, 6);
    }

    @Disabled
    @Test
    void delete_doctor_test() {
        Address address = new Address();
        address.setCity("Bstrzyna");
        address.setZip("23-221");
        address.setStreet("Mala");
        address.setNumber(3);

        Doctor doctor = new Doctor();
        doctor.setDoctorId(0);
        doctor.setFirstName("Katarzyna");
        doctor.setLastName("Rak");
        doctor.setPesel("93728137212");
        doctor.setAddress(address);
        doctor.setEmail("katarzyna@rak.com");
        doctor.setPhoneNumber("998667228");
        doctor.setSpecializationId(2);
        doctor.setPassword(doctor.getFirstName());

        doctorAdministrationDTO.add(doctor);
        int lastDoctorIndex = doctorAdministrationDTO.getAll()
                .get(doctorAdministrationDTO.getAll().size()-1).getDoctorId();
        doctorAdministrationDTO.delete(lastDoctorIndex);
    }

}
