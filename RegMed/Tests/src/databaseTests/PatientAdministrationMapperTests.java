package databaseTests;

import database.MyBatisDbConnection;
import mappers.PatientAdministrationMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pojo.Address;
import pojo.Patient;

import java.util.ArrayList;
import java.util.List;


public class PatientAdministrationMapperTests {

    @Test
    void getAllPatientsDataToTable_returning_correct_patient_list() {
        MyBatisDbConnection<PatientAdministrationMapper> dbConnection
                = new MyBatisDbConnection<>(PatientAdministrationMapper.class);

        dbConnection.openSession();
        List<Patient> result = new ArrayList<>(dbConnection.getMapper().getAllPatientsDataToTable());

        for (Patient patient: result) {
            System.out.println(patient.getAddress().toString());
        }

        assertTrue(result.size() == 3);
    }


    @Test
    void getPatient_returning_single_patient_with_id_2() {
        MyBatisDbConnection<PatientAdministrationMapper> dbConnection
                = new MyBatisDbConnection<>(PatientAdministrationMapper.class);

        dbConnection.openSession();
        Patient result = dbConnection.getMapper().getPatient(2);

        System.out.println(result.patientToString());

        assertEquals("Krzysztof", result.getFirstName());
        assertTrue(result.getPatientId() == 2);
    }

    @Disabled
    @Test
    void insert_into_test() {
        MyBatisDbConnection<PatientAdministrationMapper> dbConnection
                = new MyBatisDbConnection<>(PatientAdministrationMapper.class);
        dbConnection.openSession();

        Address a = new Address();
        a.setCity("Warszawa");
        a.setZip("21-442");
        a.setStreet("Powstancow");
        a.setNumber(23);

        Patient p = new Patient();
        p.setPatientId(0);
        p.setFirstName("Paweł");
        p.setLastName("Wołowicz");
        p.setPesel("95042411065");
        p.setAddress(a);
        p.setEmail("pawel@wolowicz.pl");
        p.setPhoneNumber("665554223");
        p.setFirstContactDoctorId(1);
        p.setPassword("password");


        dbConnection.getMapper().addAddressAsChild(p.getAddress());
        dbConnection.getMapper().addPatient(p);
        dbConnection.commit();
        dbConnection.closeSession();
    }



}
