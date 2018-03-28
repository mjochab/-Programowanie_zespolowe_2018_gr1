package repositories;

import entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements RepositoryInterface<Patient> {

    private List<Patient> patients = new ArrayList<Patient>();

    public PatientRepository() {
        seed();
    }

    @Override
    public Patient get(int id) {
        return patients.get(id);
    }

    public Patient get(String name) {
        Patient singlePatient = new Patient();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getName().equals(name) )
                singlePatient = patients.get(i);
        }
        return singlePatient;
    }

    @Override
    public List<Patient> getAll() {
        return patients;
    }

    @Override
    public void add(Patient patient) {
        patients.add(patient);
    }

    @Override
    public void remove(int id) {
        patients.remove(id);
    }

    public void remove(String name) {
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getName().equals(name))
                patients.remove(i);
            break;
        }
    }

    private void seed() {
        Patient u1 = new Patient("patient1", "patient1", "password", "123456789", "Rzeszow");
        patients.add(u1);
        Patient u2 = new Patient("patient2", "patient2", "password", "123456789", "Rzeszow");
        patients.add(u2);
    }
}
