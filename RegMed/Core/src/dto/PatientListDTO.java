package dto;

import database.MyBatisDbConnection;
import mappers.PatientListMapper;
import pojo.Patient;
import pojo.PatientList;

public class PatientListDTO {
    private MyBatisDbConnection<PatientListMapper> dbConnection;

    public PatientListDTO() {
        this.dbConnection = new MyBatisDbConnection<>(PatientListMapper.class);
    }

    public PatientList getPatientList(int doctorId) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getPatientList(doctorId);
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
