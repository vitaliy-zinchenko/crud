package zinjvi.service.impl;

import zinjvi.repository.Repository;
import zinjvi.service.Service;

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
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T find(I id) {
        return repository.find(id);
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        repository.update(entity);
    }

//    @Override
//    public T merge(T entity) {
//        return repository.merge(entity);
//    }

    @Override
    public void deleteById(I id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public Long getSize() {
        return repository.getSize();
    }

}
