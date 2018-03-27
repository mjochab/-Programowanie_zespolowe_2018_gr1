package entities;

public class Doctor extends User{
    private String pesel;
    private String address;
    private String specialization;

    public Doctor(int id, String forename, String name, String password, String pesel, String address, String specialization) {
        super(id, forename, name, password);
        this.pesel = pesel;
        this.address = address;
        this.specialization = specialization;
    }
}
