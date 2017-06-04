package io.wquach.dao;

import java.sql.ResultSet;
import java.util.function.Consumer;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
public interface EventDao {
    int insertEvent(Event event);

    void deleteEvent(int id);

    void updateEvent(Event event);

    Event getSingleEvent(int id);

    void writeAllEvents(Consumer<ResultSet> processor);
}
