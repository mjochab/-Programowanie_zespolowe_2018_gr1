package entities;

import java.util.UUID;

public class Doctor extends User{
    private String pesel;
    private String address;
    private String specialization;

    public Doctor(String forename, String name, String password, String pesel, String address, String specialization) {
        super(forename, name, password);
        this.pesel = pesel;
        this.address = address;
        this.specialization = specialization;
    }
}
