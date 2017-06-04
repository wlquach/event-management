package io.wquach.dao.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
public class EventQueryResultProcessor implements Consumer<ResultSet> {
    private static final Logger logger = LoggerFactory.getLogger(EventQueryResultProcessor.class);

    private Writer outputWriter;
    private Function<ResultSet, Event> adapter;

    public EventQueryResultProcessor(Writer outputWriter, Function<ResultSet, Event> adapter) {
        this.outputWriter = outputWriter;
        this.adapter = adapter;
    }

    @Override
    public void accept(ResultSet resultSet) {
        Event event = adapter.apply(resultSet);
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(outputWriter, event);
        } catch (IOException e) {
            logger.error("Unable to write event to output stream", e);
        }
    }
}
