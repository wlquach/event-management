package io.wquach.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Consumer;

import io.wquach.dao.Dao;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/4/17.
 */
public interface EventDao extends Dao<Event> {
    List<Event> getEventsByTitle(String title);

    void writeAll(Consumer<ResultSet> processor, Integer page);
}
