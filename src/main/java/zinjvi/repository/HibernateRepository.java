package zinjvi.repository;

/**
 * Created by zinchenko on 21.10.14.
 */
public interface HibernateRepository<T, I> extends Repository {

    T merge(T entity);

}
