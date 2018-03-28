package repositories;

import entities.Administrator;

import java.util.ArrayList;
import java.util.List;

public class AdministratorRepository implements RepositoryInterface<Administrator> {
    private List<Administrator> administrators = new ArrayList<Administrator>();

    public AdministratorRepository() {
        seed();
    }

    @Override
    public Administrator get(int id) {
        return administrators.get(id);
    }

    @Override
    public List<Administrator> getAll() {
        return administrators;
    }

    @Override
    public void add(Administrator administrator) {
        administrators.add(administrator);
    }

    @Override
    public void remove(int id) {
        administrators.remove(id);
    }

    private void seed() {
        Administrator u1 = new Administrator("admin1", "admin2", "password");
        administrators.add(u1);
        Administrator u2 = new Administrator("admin2", "admin2", "password");
    }
}
