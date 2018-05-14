package dto;

import database.MyBatisDbConnection;
import mappers.PatientModuleMapper;
import pojo.AdmissionDay;
import pojo.Patient;
import pojo.SingleVisit;

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


    public List<AdmissionDay> getAllAdmissionDays() {
        List<AdmissionDay> admissionDays = new ArrayList<>();

        AdmissionDay day1 = new AdmissionDay();
        day1.setId(1);
        day1.setDate(LocalDate.of(2018, 05, 15));

        AdmissionDay day2 = new AdmissionDay();
        day2.setId(2);
        day2.setDate(LocalDate.of(2018,06,26));

        admissionDays.add(day1);
        admissionDays.add(day2);

        return admissionDays;
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



}
