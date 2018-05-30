package dto;

import database.MyBatisDbConnection;
import mappers.PatientDataMapper;
import pojo.PatientData;

import java.util.ArrayList;

public class PatientDataDTO {
    private MyBatisDbConnection<PatientDataMapper> dbConnection;

    public PatientDataDTO()
    {
        this.dbConnection = new MyBatisDbConnection<>(PatientDataMapper.class);
    }

    public ArrayList<PatientData> getAll() {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getAllPatientDataToTable());
        }finally {
            dbConnection.closeSession();
        }
    }
}
