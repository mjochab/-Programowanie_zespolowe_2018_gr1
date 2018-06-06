package dto;

import database.MyBatisDbConnection;

import mappers.PatientRefferalMapper;
import pojo.PatientsRefferal;

public class PatientsRefferalDTO {
    private MyBatisDbConnection<PatientRefferalMapper> dbConnection;

    public PatientsRefferalDTO(){
        this.dbConnection = new MyBatisDbConnection<>(PatientRefferalMapper.class);
    }

    public void add (PatientsRefferal patientsRefferal){
        dbConnection.openSession();
        try {
            dbConnection.getMapper().addPatientsRefferal(patientsRefferal);
            dbConnection.commit();
        }finally {
            dbConnection.closeSession();
        }

    }
}
