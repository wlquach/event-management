package io.wquach.dao.jdbc.query;

import com.fasterxml.jackson.core.JsonGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Component
public class EventQueryResultProcessorFactory implements QueryResultProcessorFactory<Event>{
    @Autowired
    @Qualifier("jdbcEvent")
    Function<ResultSet, Event> eventQueryResultAdapter;

    @Override
    public QueryResultProcessor<Event> get(JsonGenerator jsonGen) {
        return new QueryResultProcessor<>(jsonGen, eventQueryResultAdapter::apply);
    }
}
