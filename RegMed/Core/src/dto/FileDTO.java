package dto;

import database.MyBatisDbConnection;
import mappers.FileMapper;
import pojo.Doctor;
import pojo.File;
import pojo.Patient;

import java.util.ArrayList;
import java.util.List;

public class FileDTO {
    private MyBatisDbConnection<FileMapper> dbConnection;

    public FileDTO() {
        this.dbConnection = new MyBatisDbConnection<>(FileMapper.class);
    }

    public List<File> getFiles(int patientId) {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getPatientFiles(patientId));
        } finally {
            dbConnection.closeSession();
        }
    }
}
