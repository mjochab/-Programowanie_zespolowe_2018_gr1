package repositories;

import entities.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements RepositoryInterface<Doctor> {

    private List<Doctor> doctors = new ArrayList<Doctor>();

    public DoctorRepository() {

    }


    @Override
    public Doctor get(int id) {
        Doctor doctorToReturn = new Doctor();

        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                doctorToReturn = doctors.get(i);
            }
        }
        return doctorToReturn;
        //return doctors.get(id);
    }

    @Override
    public Doctor get(String name, String pesel) {
        Doctor singleDoctor = new Doctor();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getName().equals(name) && doctors.get(i).getPesel().equals(pesel)) {
                singleDoctor = doctors.get(i);
            }
        }
        return singleDoctor;
    }


    @Override
    public List<Doctor> getAll() {
        return doctors;
    }

    @Override
    public void add(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public void remove(Doctor doctor) {
        doctors.remove(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        Doctor doctorToUpdate = doctors.get(doctor.getId());

        doctorToUpdate.setName(doctor.getName());
        doctorToUpdate.setForename(doctor.getForename());
        doctorToUpdate.setPesel(doctor.getPesel());
        doctorToUpdate.setAddress(doctor.getAddress());
        doctorToUpdate.setSpecialization(doctor.getSpecialization());
    }

    public int getMaxId() {
        int maxId = doctors.get(doctors.size()-1).getId();
        return maxId;
    }

    @Override
    public int getNewMaxId() {
        int maxId = doctors.get(doctors.size()-1).getId();
        int newMaxId = maxId + 1;
        return newMaxId;
    }


    private void seed() {
        Doctor d1 = new Doctor(1, "Doctor1Forename", "Doctor1Name", "password", "123456789", "Rzeszow", "Endokrynolog");
        doctors.add(d1);
        Doctor d2 = new Doctor(2, "Doctor2Forename", "Doctor2Name", "password", "987654321", "Wrocław", "Kardiolog");
        doctors.add(d2);
    }


}
