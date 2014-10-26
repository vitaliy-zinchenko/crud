package zinjvi.repository.impl;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import zinjvi.repository.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by zinchenko on 22.10.14.
 */
public class BaseHibernateRepository<T, I> extends HibernateRepository implements Repository<T, I> {

    private Class<?> eClass;

    public BaseHibernateRepository() {
        this.eClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        return createCriteria(eClass).list();
    }

    @Override
    public T find(I id) {
        return (T) createCriteria(eClass).add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

//    @Override
//    public T merge(T entity) {
//        return (T) getSession().merge(entity);
//    }

    @Override
    public void deleteById(I id) {
        T entity = find(id);
        getSession().delete(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public Long getSize() {
        return (Long) getSession().createCriteria(eClass)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
