package dto;

import database.MyBatisDbConnection;
import mappers.PatientsHistoryMapper;
import pojo.PatientsHistory;

public class PatientsHistoryDTO {
    private MyBatisDbConnection<PatientsHistoryMapper> dbConnection;

    public PatientsHistoryDTO(){
        this.dbConnection = new MyBatisDbConnection<>(PatientsHistoryMapper.class);
    }

    public void add (PatientsHistory patientsHistory){
        dbConnection.openSession();
        try {
            dbConnection.getMapper().addPatientsHistory(patientsHistory);
            dbConnection.commit();
        }finally {
            dbConnection.closeSession();
        }

    }
}
