package repositories;

import entities.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements RepositoryInterface<Doctor> {

    private List<Doctor> doctors = new ArrayList<Doctor>();

    public DoctorRepository() {
        seed();
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
        Doctor doctorToUpdate = get(doctor.getId());

        doctorToUpdate.setName(doctor.getName());
        doctorToUpdate.setForename(doctor.getForename());
        doctorToUpdate.setPesel(doctor.getPesel());
        doctorToUpdate.setAddress(doctor.getAddress());
        doctorToUpdate.setSpecialization(doctor.getSpecialization());
    }

    public int getMaxId() {
        int maxId = 0;
        if (doctors.size() > 1)
            maxId = doctors.get(doctors.size()-1).getId();
        return maxId;
    }

    @Override
    public int getNewMaxId() {
        int maxId = getMaxId();
        int newMaxId = maxId + 1;
        return newMaxId;
    }


    private void seed() {
        Doctor d1 = new Doctor(1, "Sebastian", "Niesielski", "password", "96785622368", "Rzeszow", "Endokrynolog");
        doctors.add(d1);
        Doctor d2 = new Doctor(2, "Patryk", "Rogalski", "password", "98765432132", "Wrocław", "Kardiolog");
        doctors.add(d2);
    }


}
