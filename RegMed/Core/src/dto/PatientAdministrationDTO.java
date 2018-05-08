package dto;

import database.MyBatisDbConnection;
import mappers.PatientAdministrationMapper;
import pojo.Address;
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
        try {
            ArrayList<Patient> listToReturn = new ArrayList<>(dbConnection.getMapper().getAllPatientsDataToTable());
            return listToReturn;
        } finally {
            dbConnection.closeSession();
        }
    }

    public Patient get(int patientId) {
        dbConnection.openSession();
        try {
            Patient patientToReturn = dbConnection.getMapper().getPatient(patientId);
            return patientToReturn;
        }finally {
            dbConnection.closeSession();
        }
    }

    public void add(Patient patient) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().addPatientAddressAsChild(patient.getAddress());
            dbConnection.getMapper().addPatient(patient);
            dbConnection.commit();
        }finally {
            dbConnection.closeSession();
        }
    }
    
    public void update(Patient patient) {
        dbConnection.openSession();
        try {
            Patient patientToUpdate = dbConnection.getMapper()
                    .getPatient(patient.getId());

            patientToUpdate.setFirstName(patient.getFirstName());
            patientToUpdate.setLastName(patient.getLastName());
            patientToUpdate.setPesel(patient.getPesel());
            patientToUpdate.setEmail(patient.getEmail());
            patientToUpdate.setPhoneNumber(patient.getPhoneNumber());

            dbConnection.getMapper().updatePatient(patientToUpdate);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }

    }
    
    public void updateAddress(Address address) {
        dbConnection.openSession();
        try {
            Address addressToUpdate = address;

            addressToUpdate.setCity(address.getCity());
            addressToUpdate.setZip(address.getZip());
            addressToUpdate.setStreet(address.getStreet());
            addressToUpdate.setNumber(address.getNumber());

            dbConnection.getMapper().updatePatientAddress(addressToUpdate);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public void updateFirstcontactDoctorId(Patient patient, int doctorId) {
        dbConnection.openSession();
        try {
            Patient patientToUpdate = patient;

            dbConnection.getMapper().updatePatientFirstcontactDoctor(patientToUpdate, doctorId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

}
