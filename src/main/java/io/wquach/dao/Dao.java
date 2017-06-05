package io.wquach.dao;

import java.sql.ResultSet;
import java.util.function.Consumer;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
public interface Dao<T> {
    int insert(T event);

    void delete(int id);

    void update(T event);

    T getSingle(int id);

    void writeAll(Consumer<ResultSet> processor);
}
