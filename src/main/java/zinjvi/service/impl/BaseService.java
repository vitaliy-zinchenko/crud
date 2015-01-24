package zinjvi.service.impl;

import zinjvi.repository.Repository;
import zinjvi.service.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zinchenko on 23.10.14.
 */
public abstract class BaseService<T, I> implements Service<T, I> {

    private Repository<T, I> repository;

    public BaseService(Repository<T, I> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public T find(I id) {
        return repository.find(id);
    }

    @Override
    @Transactional
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        repository.update(entity);
    }

//    @Override
//    public T merge(T entity) {
//        return repository.merge(entity);
//    }

    @Override
    @Transactional
    public void deleteById(I id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional
    public Long getSize() {
        return repository.getSize();
    }

}
