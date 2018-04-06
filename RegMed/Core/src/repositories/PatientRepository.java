package repositories;

import entities.Patient;

import java.util.ArrayList;
import java.util.List;
public class PatientRepository implements RepositoryInterface<Patient>
 {

    private List<Patient> patients = new ArrayList<Patient>();

    public PatientRepository() {
        seed();
    }

    @Override
    public Patient get(int id) {
        Patient patientToReturn = new Patient();

        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == id) {
                patientToReturn = patients.get(i);
            }
        }
        return patientToReturn;
        //return patients.get(id);
    }

     @Override
     public Patient get(String name, String pesel) {
         Patient singlePatient = new Patient();
         for (int i = 0; i < patients.size(); i++) {
             if (patients.get(i).getName().equals(name) && patients.get(i).getPesel().equals(pesel)) {
                 singlePatient = patients.get(i);
             }
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


    public void remove(Patient patient) {
        patients.remove(patient);
    }

     @Override
     public void update(Patient patient) {
         Patient patientToUpdate = get(patient.getId());

         patientToUpdate.setName(patient.getName());
         patientToUpdate.setForename(patient.getForename());
         patientToUpdate.setPesel(patient.getPesel());
         patientToUpdate.setAddress(patient.getAddress());

     }

//     public void update(int id, Patient patientChanged) {
//        Patient patientToUpdate = get(id);
//
//        patientToUpdate.
//
//     }


    private void seed() {
        Patient u1 = new Patient(1, "Szymon", "Korzeniowski", "password", "87294821984", "Rzeszow");
        patients.add(u1);
        Patient u2 = new Patient(2, "Magdalena", "Porodowicz", "password", "89574839835", "Szczecin");
        patients.add(u2);
        Patient u3 = new Patient(3, "Grzegorz", "Brzeczyszczykiewicz", "password", "239823512346", "Brzozów");
        patients.add(u3);
        Patient u4 = new Patient(4, "Martyna", "Czepielówna", "password", "95829484732", "Krynica Zdrój");
        patients.add(u4);
    }

    public int getMaxId() {
//        int maxId = 0;
//        for (int i = 0; i < patients.size(); i++) {
//            if (patients.get(i).getId() > maxId)
//                maxId = patients.get(i).getId();
//        }
        int maxId = patients.get(patients.size()-1).getId();
        return maxId;
    }

    public int getNewMaxId() {
        int maxId = patients.get(patients.size()-1).getId();
        int newMaxId = maxId + 1;
        return newMaxId;
    }

}
