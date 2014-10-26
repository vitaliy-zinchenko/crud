package zinjvi.service;

import java.util.List;

/**
 * Created by zinchenko on 21.10.14.
 */
public interface Service<T, I> {

    List<T> findAll();

    T find(I id);

    void save(T entity);

    void update(T entity);

//    T merge(T entity);

    void deleteById(I id);

    void delete(T entity);

    Long getSize();

}
