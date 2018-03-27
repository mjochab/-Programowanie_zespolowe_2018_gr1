package entities;

public abstract class User {
    private int id;
    private String forename;
    private String name;
    private String password;

    public User(int id, String forename, String name, String password) {
        this.id = id;
        this.forename = forename;
        this.name = name;
        this.password = password;
    }
}
