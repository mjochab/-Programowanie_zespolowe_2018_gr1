package dto;

import database.MyBatisDbConnection;
import mappers.PatientModuleMapper;
import pojo.Patient;

public class PatientModuleDTO {

    private MyBatisDbConnection<PatientModuleMapper> db;

    public PatientModuleDTO() {
        this.db = new MyBatisDbConnection<>(PatientModuleMapper.class);
    }


    public Patient get(int patientId) {
        db.openSession();
        try {
            Patient patient = db.getMapper().getPatient(patientId);
            return patient;
        } finally {
            db.closeSession();
        }
    }



}
