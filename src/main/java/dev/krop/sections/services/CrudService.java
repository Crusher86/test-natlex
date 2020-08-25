package dev.krop.sections.services;

import java.util.List;

public interface CrudService<T> {

    List<T> getAll();
    T getByName(String name);
    T add(T section);
    void update(T section);
    void delete(String name);
}
