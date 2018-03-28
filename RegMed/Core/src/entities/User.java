package entities;

import java.util.UUID;

public abstract class User {
    private UUID id;
    private String forename;
    private String name;
    private String password;

    public User(String forename, String name, String password) {
        id = UUID.randomUUID();
        this.forename = forename;
        this.name = name;
        this.password = password;
    }

    public User() {};

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
}
