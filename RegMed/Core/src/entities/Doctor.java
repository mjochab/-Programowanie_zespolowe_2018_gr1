package entities;

import java.util.UUID;

public class Doctor extends User{
    private String address;
    private String specialization;

    public Doctor(int id, String forename, String name, String password, String pesel, String address, String specialization) {
        super(id, forename, name, password, pesel);
        this.address = address;
        this.specialization = specialization;
    }

    public Doctor() {}

    public Doctor(Doctor doctor) {
        super(doctor.getId(), doctor.getForename(), doctor.getName(), doctor.getPassword(), doctor.getPesel());
        this.address = doctor.getAddress();
        this.specialization = doctor.getSpecialization();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
