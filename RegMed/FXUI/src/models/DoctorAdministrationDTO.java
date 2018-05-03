package models;

import database.MyBatisDbConnection;
import mappers.DoctorAdministrationMapper;
import pojo.Address;
import pojo.Doctor;

import java.util.ArrayList;

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

    public void add(Doctor doctor) {
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
                    .getDoctor(doctor.getDoctorId());

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



}
