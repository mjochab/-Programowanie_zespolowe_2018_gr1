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
