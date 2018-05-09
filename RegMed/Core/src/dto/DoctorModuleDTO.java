package dto;

import database.MyBatisDbConnection;
import mappers.DoctorModuleMapper;
import pojo.Doctor;
import pojo.DoctorWorkingDays;

import java.util.ArrayList;

public class DoctorModuleDTO {
    private MyBatisDbConnection<DoctorModuleMapper> dbConnection;

    public DoctorModuleDTO() {
        this.dbConnection = new MyBatisDbConnection<>(DoctorModuleMapper.class);
    }

    public Doctor get(int doctorId) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getDoctor(doctorId);
        } finally {
            dbConnection.closeSession();
        }
    }

    public ArrayList<DoctorWorkingDays> getDoctorWorkingDays(int doctorId) {
        dbConnection.openSession();
        try {
            return new ArrayList<DoctorWorkingDays>(dbConnection.getMapper().getDoctorWorkingDays(doctorId));
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

    public void update(DoctorWorkingDays doctorWorkingDays) {
        dbConnection.openSession();
        try {


            dbConnection.getMapper().updateDoctorWorkingDays(doctorWorkingDays);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }
}
