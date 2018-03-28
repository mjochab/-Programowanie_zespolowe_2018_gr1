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
        return doctors.get(id);
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
    public void remove(int id) {
        doctors.remove(id);
    }

    private void seed() {
        Doctor d1 = new Doctor("Doctor1Forename", "Doctor1Name", "password", "123456789", "Rzeszow", "Endokrynolog");
        doctors.add(d1);
        Doctor d2 = new Doctor("Doctor2Forename", "Doctor2Name", "password", "987654321", "Wrocław", "Kardiolog");
    }


}
