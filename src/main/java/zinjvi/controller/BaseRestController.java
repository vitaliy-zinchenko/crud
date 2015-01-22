package zinjvi.controller;

/**
 * Created by zinchenko on 23.10.14.
 */

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zinjvi.service.Service;

import java.util.List;

/**
 * Created by zinchenko on 17.09.14.
 */
public abstract class BaseRestController<T, I> {

    private Service<T, I> service;

    protected BaseRestController(Service<T, I> service) {
        this.service = service;
    }

    @RequestMapping()
    public @ResponseBody
    List<T> getAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    T get(@PathVariable I id) {
        return service.find(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    T save(@RequestBody T entity) {
        service.save(entity);
        return entity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    T update(@RequestBody T entity) {
        service.update(entity);
        return entity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteById(@PathVariable I id) {
        service.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody
    void delete(@RequestBody T entity) {
        service.delete(entity);
    }

}

