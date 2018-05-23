package entities;

import pojo.Doctor;

import java.util.Random;
import java.util.UUID;

public class Patient extends User {
    private String address;
    private Doctor firstcontactDoctor;
    //phone no


    public Patient(int id, String forename, String name, String password, String pesel, String address) {
        super(id, forename, name, password, pesel);
        this.address = address;
    }


    public Patient() {

    }

    public Patient(Patient patient) {
        super(patient.getId(), patient.getForename(), patient.getName(), patient.getPassword(), patient.getPesel());
        this.address = patient.getAddress();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Doctor getFirstcontactDoctor() {
        return firstcontactDoctor;
    }

    public void setFirstcontactDoctor(Doctor firstcontactDoctor) {
        this.firstcontactDoctor = firstcontactDoctor;
    }
}
