package dto;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import database.MyBatisDbConnection;
import javafx.scene.control.ChoiceBox;
import mappers.PatientModuleMapper;
import pojo.*;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
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


    public List<LocalDate> getAdmissionDaysDatesForDoctor(int doctorId) {
        db.openSession();
        try {
            return new ArrayList<>(
                    db.getMapper().getAdmissionDaysDatesForDoctor(doctorId));

        } finally {
            db.closeSession();
        }
    }

    public List<AdmissionDay2> getAdmissionDaysForDoctor(int doctorId) {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getAdmissionDaysForDoctor(doctorId));
        } finally {
            db.closeSession();
        }
    }

    public AdmissionDay2 getAdmissionDayForVisitPicker(LocalDate admissionDayDate, int doctorId) {
        db.openSession();
        try {
            AdmissionDay2 result = db.getMapper().getAdmissionDayByDate(admissionDayDate, doctorId);
            return result;
        } finally {
            db.closeSession();
        }
    }

    public SingleVisit getSingleVisitById(int singleVisitId) {
        db.openSession();
        try {
            return db.getMapper().getSingleVisit(singleVisitId);
        } finally {
            db.closeSession();
        }
    }

    public List<SingleVisit> getSingleVisitsFromDate(LocalDate visitDate, int doctorId) {
        db.openSession();
        try {
            List<SingleVisit> result = new ArrayList<>(db.getMapper().getSingleVisitsFromDate(visitDate, doctorId));
            return result;
        } finally {
            db.closeSession();
        }
    }



    public List<SingleVisit> getAllVisits(AdmissionDay2 admissionDay) {
        LocalDate visitsDate = admissionDay.getDate();
        int doctorId = admissionDay.getDoctor().getId();

        List<SingleVisit> lockedVisits = new ArrayList<>(getSingleVisitsFromDate(visitsDate, doctorId));
        List<SingleVisit> freeVisits = new ArrayList<>();
        List<SingleVisit> allDayVisits = new ArrayList<>();

        int interval = admissionDay.getHourInterval();

        LocalTime hourFrom = admissionDay.getHourFrom();
        LocalTime hourTo = admissionDay.getHourTo();
        LocalTime currentTime = admissionDay.getHourFrom();

        do {
            SingleVisit singleVisit = new SingleVisit();
            singleVisit.setVisitHour(currentTime);
            freeVisits.add(singleVisit);

            currentTime = currentTime.plusMinutes(interval);
        } while(!currentTime.equals(hourTo));

        if(lockedVisits.size() == 0) {
            return freeVisits;
        }

        allDayVisits = new ArrayList<>(freeVisits);

        for (SingleVisit lockedVisit : lockedVisits) {
            for (int i = 0; i < freeVisits.size(); i++) {
                if (lockedVisit.getVisitHour().getHour() == freeVisits.get(i).getVisitHour().getHour()
                        && lockedVisit.getVisitHour().getMinute() == freeVisits.get(i).getVisitHour().getMinute()) {
                    allDayVisits.set(i, lockedVisit);
                    break;
                }
            }
        }

        return allDayVisits;
    }

    public List<SingleVisit> getAllFreeVisits(AdmissionDay2 admissionDay) {
        LocalDate visitsDate = admissionDay.getDate();
        int doctorId = admissionDay.getDoctor().getId();

        List<SingleVisit> lockedVisits = new ArrayList<>(getSingleVisitsFromDate(visitsDate, doctorId));
        List<SingleVisit> freeVisits = new ArrayList<>();
        List<SingleVisit> allDayVisits = new ArrayList<>();

        int interval = admissionDay.getHourInterval();

        LocalTime hourFrom = admissionDay.getHourFrom();
        LocalTime hourTo = admissionDay.getHourTo();
        LocalTime currentTime = admissionDay.getHourFrom();

        do {
            SingleVisit singleVisit = new SingleVisit();
            singleVisit.setVisitHour(currentTime);
            freeVisits.add(singleVisit);

            currentTime = currentTime.plusMinutes(interval);
        } while(!currentTime.equals(hourTo));

        for (SingleVisit lockedVisit : lockedVisits) {
            for (int i = 0; i < freeVisits.size(); i++) {
                if (lockedVisit.getVisitHour().getHour() == freeVisits.get(i).getVisitHour().getHour()
                        && lockedVisit.getVisitHour().getMinute() == freeVisits.get(i).getVisitHour().getMinute()) {
                    //allDayVisits.set(i, lockedVisit);
                    freeVisits.remove(freeVisits.get(i));
                    break;
                }
            }
        }

        return freeVisits;
    }

    public List<SingleVisit> getFirst10FreeSingleVisits(int doctorId) {
        LocalDate date = LocalDate.now();
        List<SingleVisit> result = new ArrayList<>();
        //List<AdmissionDay2> admissionDays = new ArrayList<>(getAdmissionDaysForDoctor(doctorId));
        List<AdmissionDay2> admissionDays = new ArrayList<>(
                admissionDaysAfterDate(getAdmissionDaysForDoctor(doctorId), date));

        int counter = 0;
        while (result.size() < 9 && counter < admissionDays.size()) {
            List<SingleVisit> tempVisits = new ArrayList<>(getAllFreeVisits(admissionDays.get(counter)));
            setAdmissionDayToFreeVisitList(tempVisits, admissionDays.get(counter));
            result.addAll(tempVisits);

            counter++;
            date = date.plusDays(1);
        }

        if (result.size() > 10) {
            int nOfElemToDelete = (10-result.size())*(-1);

            for (int i = 0; i < nOfElemToDelete; i++) {
                result.remove(result.size()-1);
            }
        }

        return result;
    }

    private List<AdmissionDay2> admissionDaysAfterDate(List<AdmissionDay2> admissionDays, LocalDate date) {
        List<AdmissionDay2> result = new ArrayList<>();

        for (AdmissionDay2 aDay : admissionDays) {
            if (!aDay.getDate().isBefore(date)) {
                result.add(aDay);
            }
        }

        //result.sort((o1, o2) -> -1);
        result.sort(Comparator.comparing(o -> o.getDate()));
        return result;
    }

    public List<SingleVisit> getVisitsAfterToday(Patient patient) {
        LocalDate today = LocalDate.now();
        List<SingleVisit> result = new ArrayList<>();
        List<SingleVisit> allPatientVisits = new ArrayList<>(getSingleVisitsForPatient(patient));

        for(SingleVisit visit : allPatientVisits) {
            if (visit.getAdmissionDay2().getDate().isAfter(today) ||
                    visit.getAdmissionDay2().getDate().isEqual(today)) {
                result.add(visit);
            }
        }

        result.sort(Comparator.comparing(o -> o.getAdmissionDay2().getDate()));
        return result;
    }

    private void setAdmissionDayToFreeVisitList(List<SingleVisit> singleVisits, AdmissionDay2 admissionDay) {
        for(SingleVisit visit : singleVisits) {
            setAdmissionDayToFreeVisit(visit, admissionDay);
        }
    }

    private void setAdmissionDayToFreeVisit(SingleVisit singleVisit, AdmissionDay2 admissionDay2) {
        singleVisit.setAdmissionDay2(admissionDay2);
    }


    public List<AdmissionDay2> admissionDaysBetweenDates(LocalDate start, LocalDate end, int doctorId) {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getAdmissionDaysBetweenDates(start, end, doctorId));
        } finally {
            db.closeSession();
        }
    }

    public List<AdmissionDay2> admissionDaysFullOfVisits(List<AdmissionDay2> listOfDaysToCheck, int doctorId) {
        List<AdmissionDay2> result = new ArrayList<>();

        for (AdmissionDay2 admissionDay : listOfDaysToCheck) {
            List<SingleVisit> visits = new ArrayList<>(getAllVisits(admissionDay));
            if (checkIfDayHaveFreeVisits(visits) == false) {
                result.add(admissionDay);
            }

        }

        return result;
    }

    private boolean checkIfDayHaveFreeVisits(List<SingleVisit> singleVisits) {

        for (SingleVisit s : singleVisits) {

            if (s.getPatient() == null) {
                return true;
            }
        }

        return false;
    }


    public void addSingleVisit(SingleVisit singleVisit) {
        db.openSession();
        try {
            db.getMapper().createNewSingleVisit(singleVisit);
            db.commit();
        } finally {
            db.closeSession();
        }
    }

    public List<SingleVisit> getSingleVisitsForPatient(Patient patient) {
        db.openSession();
        try {
            return new ArrayList<>(db.getMapper().getSingleVisitsForPatient(patient));
        } finally {
            db.closeSession();
        }
    }



}
