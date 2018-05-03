package models;

import database.MyBatisDbConnection;
import mappers.AdminAdministrationMapper;
import pojo.Administrator;

import java.util.ArrayList;

public class AdminAdministrationDTO {

    private MyBatisDbConnection<AdminAdministrationMapper> dbConnection;

    public AdminAdministrationDTO() {
        this.dbConnection = new MyBatisDbConnection<>(AdminAdministrationMapper.class);
    }

    public ArrayList<Administrator> getAll() {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getAllAdministratorsToTable());
        } finally {
            dbConnection.closeSession();
        }
    }

    public Administrator get(int adminId) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getAdministrator(adminId);
        } finally {
            dbConnection.closeSession();
        }
    }

    public void add(Administrator administrator) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().addAdministrator(administrator);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }


    public void update(Administrator administrator) {
        dbConnection.openSession();
        try {
            Administrator adminToUpdate = dbConnection.getMapper()
                    .getAdministrator(administrator.getAdminId());

            adminToUpdate.setFirstName(administrator.getFirstName());
            adminToUpdate.setLastName(administrator.getLastName());
            adminToUpdate.setPesel(administrator.getPesel());
            adminToUpdate.setEmail(administrator.getEmail());
            adminToUpdate.setPhoneNumber(administrator.getPhoneNumber());
            dbConnection.getMapper().updateAdministrator(adminToUpdate);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }


    public void delete(int adminId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().deleteAdministrator(adminId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

}
