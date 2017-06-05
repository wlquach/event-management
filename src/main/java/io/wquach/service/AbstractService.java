package io.wquach.service;

import java.util.function.Consumer;

import io.wquach.dao.Dao;
import io.wquach.domain.Event;
import io.wquach.domain.Identifiable;

/**
 * Created by wquach on 6/4/17.
 */
public abstract class AbstractService<T extends Identifiable> implements CrudService<T> {

    @Override
    public int add(T object) {
        return getDao().insert(object);
    }

    @Override
    public void update(T object) {
        getDao().update(object);
    }

    @Override
    public void getAll(Consumer processor) {
        getDao().writeAll(processor);
    }

    @Override
    public T getOne(int id) {
        return getDao().getSingle(id);
    }

    @Override
    public void deleteSingle(int id) {
        getDao().delete(id);
    }

    protected abstract Dao<T> getDao();
}
