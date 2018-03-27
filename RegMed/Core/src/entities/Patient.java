package entities;

public class Patient extends User {
    private String pesel;
    private String address; //TODO: Address should be separated


    public Patient(int id, String forename, String name, String password, String pesel, String address) {
        super(id, forename, name, password);
        this.pesel = pesel;
        this.address = address;
    }
}
