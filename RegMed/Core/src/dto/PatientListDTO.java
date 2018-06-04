package dto;

import database.MyBatisDbConnection;
import mappers.PatientListMapper;
import pojo.Patient;
import pojo.PatientList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PatientListDTO {
    private MyBatisDbConnection<PatientListMapper> dbConnection;

    public PatientListDTO() {
        this.dbConnection = new MyBatisDbConnection<>(PatientListMapper.class);
    }

    public ArrayList<PatientList> getPatientList(int doctorId) {
        dbConnection.openSession();
        try {
            ArrayList<PatientList> result = new ArrayList<PatientList>(dbConnection.getMapper().getPatientList(doctorId));
            result.sort(Comparator.comparing(o -> o.getVisitHour()));
            return result;
        } finally {
            dbConnection.closeSession();
        }
    }

    public Patient getFirstLastName(int patientId) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getFirstLastName(patientId);
        } finally {
            dbConnection.closeSession();
        }
    }
}
