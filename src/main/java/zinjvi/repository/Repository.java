package zinjvi.repository;

import java.util.List;

/**
 * Created by zinchenko on 21.10.14.
 */
public interface Repository<T, I> {

    List<T> findAll();

    T find(I id);

    void save(T entity);

    void update(T entity);

    void deleteById(I id);

    void delete(T entity);

    Long getSize();

}
