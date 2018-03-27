package repositories;

import entities.User;

import java.util.List;

public interface RepositoryInterface<T> {

    T get(int id);
    List<T> getAll();
    void add(T t);
    void remove(int id);
}
