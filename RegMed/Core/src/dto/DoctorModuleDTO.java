package dto;

import database.MyBatisDbConnection;
import javafx.collections.ObservableList;
import mappers.DoctorModuleMapper;
import pojo.Doctor;
import pojo.DoctorWorkingDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorModuleDTO {
    private MyBatisDbConnection<DoctorModuleMapper> dbConnection;

    public DoctorModuleDTO() {
        this.dbConnection = new MyBatisDbConnection<>(DoctorModuleMapper.class);

    }

    public Doctor getDoctor(int id) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getDoctor(id);
        } finally {
            dbConnection.closeSession();
        }
    }

    public HashMap<String, DoctorWorkingDays> getDoctorWorkingDays(int id) {
        dbConnection.openSession();
        try {
            return tableDataToHashMap(dbConnection.getMapper().getDoctorWorkingDays(id));
        } finally {
            dbConnection.closeSession();
        }
    }

    public void add(DoctorWorkingDays doctorWorkingDays) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().addDoctorWorkingDays(doctorWorkingDays);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }


    public HashMap tableDataToHashMap(List<DoctorWorkingDays> tableData){
        HashMap<String, DoctorWorkingDays> days = new HashMap<String, DoctorWorkingDays>();
        for (DoctorWorkingDays day:tableData) {
            days.put(day.getDay(), day);
        }
        return days;
    }
}
