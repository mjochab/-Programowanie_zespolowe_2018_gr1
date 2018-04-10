package entities;

import java.util.UUID;

public class Administrator extends User {


    public Administrator(int id, String forename, String name, String password, String pesel) {
        super(id, forename, name, password, pesel);
    }
    public Administrator() {};

    public Administrator(Administrator administrator) {
        super(administrator.getId(), administrator.getForename(), administrator.getName(),
                administrator.getPassword(), administrator.getPesel());
    }
}
