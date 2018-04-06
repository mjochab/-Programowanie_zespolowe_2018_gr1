package repositories;

import entities.User;

import java.util.List;

public interface RepositoryInterface<T> {

    T get(int id);
    T get(String name, String pesel);
    List<T> getAll();
    void add(T t);
    void remove(T t);

    //dodać metodę update/save
    void update(T t);
}
