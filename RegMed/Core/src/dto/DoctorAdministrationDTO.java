package dto;

import database.MyBatisDbConnection;
import mappers.DoctorAdministrationMapper;
import pojo.Address;
import pojo.Doctor;
import pojo.Specialization;

import java.util.ArrayList;
import java.util.List;

/**
 * Holding methods using to operate on DoctorAdministration mapper. These methods are using MyBatisDbConnection class,
 * which holding mapped methods using to operate on database.
 * Class in methods make sure if the connection is always closed.
 *
 * @author Szymon P
 */
public class DoctorAdministrationDTO {

    private MyBatisDbConnection<DoctorAdministrationMapper> dbConnection;

    public DoctorAdministrationDTO() {
        this.dbConnection = new MyBatisDbConnection<>(DoctorAdministrationMapper.class);
    }

    public ArrayList<Doctor> getAll() {
        dbConnection.openSession();
        try {
            return new ArrayList<Doctor>(dbConnection.getMapper().getAllDoctorsToTable());
        } finally {
            dbConnection.closeSession();
        }
    }

    public Doctor get(int doctorId) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getDoctor(doctorId);
        } finally {
            dbConnection.closeSession();
        }
    }

    public void add(Doctor doctor){

        dbConnection.openSession();
        try {
            dbConnection.getMapper().addDoctorAddressAsChild(doctor.getAddress());
            dbConnection.getMapper().addDoctor(doctor);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public void update(Doctor doctor) {
        dbConnection.openSession();
        try {
            Doctor doctorToUpdate = dbConnection.getMapper()
                    .getDoctor(doctor.getId());

            doctorToUpdate.setFirstName(doctor.getFirstName());
            doctorToUpdate.setLastName(doctor.getLastName());
            doctorToUpdate.setPesel(doctor.getPesel());
            doctorToUpdate.setEmail(doctor.getEmail());
            doctorToUpdate.setPhoneNumber(doctor.getPhoneNumber());
            dbConnection.getMapper().updateDoctor(doctorToUpdate);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public void updateAddress(Address address) {
        dbConnection.openSession();
        try {
            Address addressToUpdate = dbConnection.getMapper()
                    .selectAddress(address.getAddressId());

            addressToUpdate.setCity(address.getCity());
            addressToUpdate.setZip(address.getZip());
            addressToUpdate.setStreet(address.getStreet());
            addressToUpdate.setNumber(address.getNumber());

            dbConnection.getMapper().updateDoctorAddress(addressToUpdate);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public void updateSpecialization(Doctor doctor, int specializationId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().updateDoctorSpecialization(doctor, specializationId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public void delete(int doctorId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().deleteDoctor(doctorId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public List<Specialization> getAllSpecializations() {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getAllSpecializations());
        } finally {
            dbConnection.closeSession();
        }
    }

    public void createSpecialization(Specialization specialization) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().createSpeciatization(specialization);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public void deleteSpecialization(int specializationId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().deleteSpecialization(specializationId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }



}
