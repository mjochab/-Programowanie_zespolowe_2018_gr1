package databaseTests;

import database.MyBatisDbConnection;
import mappers.PatientAdministrationMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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

        assertTrue(result.size() == 2);
    }

}
