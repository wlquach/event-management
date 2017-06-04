package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.sql.ResultSet;
import java.util.function.Function;

import io.wquach.dao.EventQueryResultProcessorFactory;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Component
@Qualifier("jdbc")
public class JdbcEventQueryResultProcessorFactory implements EventQueryResultProcessorFactory {
    @Autowired
    @Qualifier("jdbc")
    Function<ResultSet, Event> eventQueryResultAdapter;

    @Override
    public EventQueryResultProcessor get(Writer outputWriter) {
        return new EventQueryResultProcessor(outputWriter, eventQueryResultAdapter::apply);
    }
}
