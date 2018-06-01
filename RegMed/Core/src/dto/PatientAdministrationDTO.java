package dto;

import database.MyBatisDbConnection;
import mappers.PatientAdministrationMapper;
import pojo.*;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public void delete(int patientId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().deletePatient(patientId);
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

    public List<Doctor> getAllFirstcontactDoctors() {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getAllFirstContactDoctors());
        } finally {
            dbConnection.closeSession();
        }
    }

    public List<SingleVisit> getVisitsForPatient(int patientId) {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getVisitsForPatient(patientId));
        } finally {
            dbConnection.closeSession();
        }
    }

    public void deleteVisit(int visitId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().deleteVisit(visitId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public void deleteAllVisits(int patientId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().deleteAllVisits(patientId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }

    public SingleVisit getVisit(int visitId) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getVisit(visitId);
        } finally {
            dbConnection.closeSession();
        }
    }

    public List<Specialization> getVisitDoctorSpecializations() {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getSpecializations());
        } finally {
            dbConnection.closeSession();
        }
    }

    public List<Doctor> getDoctorsForVisitSpecialization(String specializationName) {
        dbConnection.openSession();
        try {
            return dbConnection.getMapper().getDoctorsForVisitSpecialization(specializationName);
        } finally {
            dbConnection.closeSession();
        }
    }

    public List<AdmissionDay2> getAdmissionDaysForDoctor(int doctorId) {
        dbConnection.openSession();
        try {
            return new ArrayList<>(dbConnection.getMapper().getAdmissionDaysForDoctor(doctorId));
        } finally {
            dbConnection.closeSession();
        }
    }

    public List<AdmissionDay2> getAdmissionDaysAfterToday(int doctorId) {
        List<AdmissionDay2> doctorAdmissionDays = getAdmissionDaysForDoctor(doctorId);
        List<AdmissionDay2> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate weekLater = today.plusWeeks(1);

        for (int i = 0; i < doctorAdmissionDays.size(); i++) {
            if (doctorAdmissionDays.get(i).getDate().equals(today)
                    || doctorAdmissionDays.get(i).getDate().isAfter(today)
               )
            {
                result.add(doctorAdmissionDays.get(i));
            }
        }

        result.sort((o1, o2) -> -1);

        return result;
    }

    public List<SingleVisit> getFreeVisits(AdmissionDay2 admissionDay) {
        PatientModuleDTO patientModuleDTO = new PatientModuleDTO();
        return patientModuleDTO.getAllFreeVisits(admissionDay);
    }

    public void updateVisitDateAndHour(int singleVisitId, LocalTime newTime, int admissionDayId) {
        dbConnection.openSession();
        try {
            dbConnection.getMapper().updateVisitDateAndHour(singleVisitId, newTime, admissionDayId);
            dbConnection.commit();
        } finally {
            dbConnection.closeSession();
        }
    }




}
