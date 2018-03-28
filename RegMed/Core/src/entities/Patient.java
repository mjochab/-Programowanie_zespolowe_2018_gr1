package entities;

import java.util.UUID;

public class Patient extends User {
    private String pesel;
    private String address; //TODO: Address should be separated
    //phone no


    public Patient(String forename, String name, String password, String pesel, String address) {
        super(forename, name, password);
        this.pesel = pesel;
        this.address = address;
    }

    public Patient() {

    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
