package io.wquach.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Consumer;

import io.wquach.domain.Event;
import io.wquach.domain.Identifiable;

/**
 * Created by wquach on 6/3/17.
 */
public interface Dao<T extends Identifiable> {
    int insert(T object);

    void delete(int id);

    void delete(T object);

    void update(T object);

    T getSingle(int id);

    void writeAll(Consumer<ResultSet> processor);
}
