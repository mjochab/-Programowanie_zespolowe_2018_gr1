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

    @Disabled
    @Test
    void update_patient_test() {
        MyBatisDbConnection<PatientAdministrationMapper> dbConnection
                = new MyBatisDbConnection<>(PatientAdministrationMapper.class);
        dbConnection.openSession();

        Patient before = dbConnection.getMapper().getPatient(3);
        Patient result = dbConnection.getMapper().getPatient(3);
        result.setFirstName("firstName");
        result.setLastName("lastName");
        result.setPesel("11111111111");
        result.setEmail("a@a.com");
        result.setPhoneNumber("111222333");

        dbConnection.getMapper().updatePatient(result);
        dbConnection.commit();
        dbConnection.closeSession();
    }

    @Disabled
    @Test
    void update_patient_address_test() {
        MyBatisDbConnection<PatientAdministrationMapper> dbConnection
                = new MyBatisDbConnection<>(PatientAdministrationMapper.class);
        dbConnection.openSession();

        Patient patient = dbConnection.getMapper().getPatient(3);
        Address before = patient.getAddress();
        Address result = patient.getAddress();
        result.setCity("cityy");
        result.setZip("11-112");
        result.setStreet("streett");
        result.setNumber(1234);

        dbConnection.getMapper().updatePatientAddress(result);
        dbConnection.commit();
        dbConnection.closeSession();
    }

    @Disabled
    @Test
    void update_firstcontactDoctorId_test() {
        MyBatisDbConnection<PatientAdministrationMapper> dbConnection
                = new MyBatisDbConnection<>(PatientAdministrationMapper.class);
        dbConnection.openSession();

        Patient patient = dbConnection.getMapper().getPatient(3);
        int before = patient.getFirstContactDoctorId();

        dbConnection.getMapper().updatePatientFirstcontactDoctor(patient, 2);
        int result = dbConnection.getMapper().getPatient(3).getFirstContactDoctorId();

        dbConnection.commit();
        dbConnection.closeSession();

        System.out.println(result);
        assertNotEquals(before, result);
    }



}
