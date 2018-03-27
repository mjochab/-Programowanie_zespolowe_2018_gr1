package repositories;

import entities.Administrator;
import entities.Doctor;
import entities.Patient;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements RepositoryInterface<User> {
    private List<User> users = new ArrayList<User>();

    public UserRepository() {
        seed();
    }

    @Override
    public User get(int id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        return users;
    }


    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void remove(int id) {
        users.remove(id);
    }

    public void seed() {
        User u1 = new Patient(1, "patient1", "patient1", "password", "123456789", "Rzeszow");
        users.add(u1);
        User u2 = new Patient(1, "patient2", "patient2", "password", "123456789", "Rzeszow");
        users.add(u2);
        User u3 = new Doctor(3, "doctor1", "doctor1", "password", "987654321", "Warszawa", "Laryngolog");
        users.add(u3);
        User u4 = new Administrator(4, "Administrator", "John", "superStrong");
        users.add(u4);
    }

}
