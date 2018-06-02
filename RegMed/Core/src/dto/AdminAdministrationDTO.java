package dto;

import database.MyBatisDbConnection;
import exceptions.ValidationException;
import mappers.AdminAdministrationMapper;
import pojo.Administrator;

import java.util.ArrayList;

/**
 * Holding database operations mapped methods for administrator administration module.
 * Class in methods make sure if the connection is always closed.
 *
 * @author Szymon P
 */
public class AdminAdministrationDTO {

    private MyBatisDbConnection<AdminAdministrationMapper> dbConnection;

    public AdminAdministrationDTO() {
        this.dbConnection = new MyBatisDbConnection<>(AdminAdministrationMapper.class);
    }

    /**
     * Getting all administrators from database.
     *
     * @return  list of all administrators from database.
     */
    public ArrayList<Administrator> getAll() {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getAllAdministratorsToTable());
        } finally {
            dbConnection.closeSession();
        }
    }

    /**
     * Getting specified admin by id.
     *
     * @param   adminId admin for take from database.
     * @return  selected admin from database.
     */
    public Administrator get(int adminId) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getAdministrator(adminId);
        } finally {
            dbConnection.closeSession();
        }
    }

    /**
     * Adding administrator object to database using methods from mapped interface.
     *
     * @param administrator         administrator to add.
     * @throws ValidationException  wrong data format tried to add to database.
     */
    public void add(Administrator administrator)  {
        //validateAdmin(administrator);

        dbConnection.openSession();
        try {
            dbConnection.getMapper().addAdministrator(administrator);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    /**
     * Updating administrator in database.
     *
     * @param administrator administrator to update. His id is used to get administrator from database,
     *                      which values are updating from object.
     */
    public void update(Administrator administrator) {
        dbConnection.openSession();
        try {
            Administrator adminToUpdate = dbConnection.getMapper()
                    .getAdministrator(administrator.getId());

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

    /**
     * Removing specified administrator from database.
     *
     * @param adminId using for specify, which administrator you want to delete.
     */
    public void delete(int adminId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().deleteAdministrator(adminId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    /**
     * Administrator validation before save to database.
     *
     * @param admin                 administrator which data you want validate.
     * @return                      returning true, when administrator which you want add to database
     *                              is correct with data policy.
     * @throws ValidationException  wrong data format tried to add to database.
     */
//    private boolean validateAdmin(Administrator admin) throws ValidationException{
//        if (!AdministrationValidators.userValidation(admin)) {
//            throw new ValidationException(AdministrationValidators.userValidationErrors(admin));
//        }
//
//        return true;
//    }

}
