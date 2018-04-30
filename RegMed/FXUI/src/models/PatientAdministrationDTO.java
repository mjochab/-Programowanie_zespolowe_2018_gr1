package models;

import database.MyBatisDbConnection;
import mappers.PatientAdministrationMapper;
import pojo.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientAdministrationDTO {

    private MyBatisDbConnection<PatientAdministrationMapper> dbConnection;


    public PatientAdministrationDTO() {
        this.dbConnection = new MyBatisDbConnection<>(PatientAdministrationMapper.class);
    }

    public ArrayList<Patient> getAll() {
        dbConnection.openSession();
        ArrayList<Patient> listToReturn = new ArrayList<>(dbConnection.getMapper().getAllPatientsDataToTable());
        dbConnection.closeSession();
        return listToReturn;
    }

}
