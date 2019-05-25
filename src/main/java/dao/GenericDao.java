package dao;

import java.util.List;

public interface GenericDao<T> {

    void add(T object);

    void delete(T object);

    void update(T object);

    T getItemById(Class<T> clazz, long id);

    List<T> getAllItems(Class<T> clazz);
}
