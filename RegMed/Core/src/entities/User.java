package entities;

import java.util.UUID;

public abstract class User {
    private int id;
    private String forename;
    private String name;
    private String password;
    private String pesel;

    public User(int id, String forename, String name, String password, String pesel) {
        this.id = id;   //System.identityHashCode(this);
        this.forename = forename;
        this.name = name;
        this.password = password;
        this.pesel = pesel;
    }

    public User() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
