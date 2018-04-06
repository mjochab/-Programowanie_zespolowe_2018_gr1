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
    public Administrator get(String name, String pesel) {
        Administrator singleAdministrator = new Administrator();
        for (int i = 0; i < administrators.size(); i++) {
            if (administrators.get(i).getName().equals(name) && administrators.get(i).getPesel().equals(pesel)) {
                singleAdministrator = administrators.get(i);
            }
        }
        return singleAdministrator;
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
    public void remove(Administrator administrator) {
        administrators.remove(administrator);
    }

    @Override
    public void update(Administrator administrator) {
        Administrator adminToUpdate = administrators.get(administrator.getId());

        adminToUpdate.setForename(administrator.getForename());
        adminToUpdate.setName(administrator.getName());
    }

    private void seed() {
        Administrator u1 = new Administrator(1, "admin1", "admin2", "password", "91234556894");
        administrators.add(u1);
        Administrator u2 = new Administrator(2, "admin2", "admin2", "password", "91475455524");
    }
}
