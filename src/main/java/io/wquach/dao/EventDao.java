package io.wquach.dao;

import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Consumer;

import io.wquach.dao.jdbc.EventQueryResultProcessor;
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
