package dto;

import database.MyBatisDbConnection;
import mappers.PatientModuleMapper;
import pojo.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientModuleDTO {

    private MyBatisDbConnection<PatientModuleMapper> db;

    public PatientModuleDTO() {
        this.db = new MyBatisDbConnection<>(PatientModuleMapper.class);
    }


    public Patient get(int patientId) {
        db.openSession();
        try {
            Patient patient = db.getMapper().getPatient(patientId);
            return patient;
        } finally {
            db.closeSession();
        }
    }

    public List<DoctorWorkingDays> getDoctorWorkingDays(int doctorId) {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getDoctorWorkingDays(doctorId));
        } finally {
            db.closeSession();
        }
    }

    public List<Doctor> getAllDoctors() {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getAllDoctors());
        } finally {
            db.closeSession();
        }
    }


    public List<AdmissionDay> getAllAdmissionDays() {
        List<AdmissionDay> admissionDays = new ArrayList<>();

        AdmissionDay day1 = new AdmissionDay();
        day1.setId(1);
        day1.setDate(LocalDate.of(2018, 05, 15));

        AdmissionDay day2 = new AdmissionDay();
        day2.setId(2);
        day2.setDate(LocalDate.of(2018,05,20));

        admissionDays.add(day1);
        admissionDays.add(day2);

        return admissionDays;
    }

    public AdmissionDay getAdmissionDayByDate(LocalDate date) {
        List<AdmissionDay> admissionDays = new ArrayList<>(getAllAdmissionDays());
        AdmissionDay admissionDay = new AdmissionDay();

        for (AdmissionDay a : admissionDays) {
            if (a.getDate().equals(date)) {
                admissionDay = a;
            }
        }

        return admissionDay;
    }



    public void addVisit() {

    }

    public List<SingleVisit> getAllVisits(AdmissionDay admissionDay) {
        List<SingleVisit> visits = new ArrayList<>();
        List<SingleVisit> visits2 = new ArrayList<>();
        List<AdmissionDay> admissionDays = getAllAdmissionDays();

        SingleVisit v1 = new SingleVisit();
        v1.setId(1);
        v1.setAdmissionDay(admissionDays.get(0));
        v1.setVisitHour(LocalTime.of(10,00));
        Patient p = new Patient();
        p.setFirstName("FurstNamePatient");
        v1.setPatient(p);
        visits.add(v1);

        SingleVisit v2 = new SingleVisit();
        v2.setId(2);
        v2.setAdmissionDay(admissionDays.get(0));
        v2.setVisitHour(LocalTime.of(10,30));
        visits.add(v2);

        SingleVisit v3 = new SingleVisit();
        v3.setId(3);
        v3.setAdmissionDay(admissionDays.get(1));
        v3.setVisitHour(LocalTime.of(20,30));
        visits.add(v3);

        for (SingleVisit visit : visits) {
            if (visit.getAdmissionDay().getId() == admissionDay.getId()) {
                visits2.add(visit);
            }
        }
        return visits2;
    }



    public List<SingleVisit> getBusyVisits(AdmissionDay admissionDay) {
        List<SingleVisit> allVisits = getAllVisits(admissionDay);
        List<SingleVisit> listToReturn = new ArrayList<>();

        for (SingleVisit visit : allVisits) {
            if (visit.getPatient() != null) {
                listToReturn.add(visit);
            }
        }

        return listToReturn;
    }

    public List<SingleVisit> getFreeVisits(AdmissionDay admissionDay) {
        List<SingleVisit> allVisits = getAllVisits(admissionDay);
        List<SingleVisit> listToReturn = new ArrayList<>();

        for (SingleVisit visit : allVisits) {
            if (visit.getPatient() == null) {
                listToReturn.add(visit);
            }
        }

        return listToReturn;
    }

    public List<String> getSpecializationsNames() {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getSpecializationsString());
        } finally {
            db.closeSession();
        }
    }

    public List<Specialization> getSpecializations() {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getSpecializations());
        } finally {
            db.closeSession();
        }
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getDoctorsBySpecialization(specialization));
        } finally {
            db.closeSession();
        }
    }



}
