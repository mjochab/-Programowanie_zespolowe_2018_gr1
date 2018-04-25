package entities;

import java.util.UUID;

public class Administrator extends User {

    private String email;
    private int id_global;
    private String phoneNumber;

    public Administrator(int id, int id_global, String forename, String name, String pesel, String email,
                         String phoneNumber, String password) {
        super(id, forename, name, password, pesel);
        this.email = email;
        this.id_global = id_global;
        this.phoneNumber = phoneNumber;
    }

    public Administrator(int id, String forename, String name, String password, String pesel) {
        super(id, forename, name, password, pesel);
    }
    public Administrator() {};

    public Administrator(Administrator administrator) {
        super(administrator.getId(), administrator.getForename(), administrator.getName(),
                administrator.getPassword(), administrator.getPesel());
    }
}
