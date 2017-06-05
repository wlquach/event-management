package io.wquach.dao.jdbc;

import com.fasterxml.jackson.core.JsonGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.function.Consumer;
import java.util.function.Function;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
public class QueryResultProcessor<T> implements Consumer<ResultSet> {
    private static final Logger logger = LoggerFactory.getLogger(QueryResultProcessor.class);

    private JsonGenerator jsonGen;
    private Function<ResultSet, T> adapter;

    public QueryResultProcessor(JsonGenerator jsonGen, Function<ResultSet, T> adapter) {
        this.jsonGen = jsonGen;
        this.adapter = adapter;
    }

    @Override
    public void accept(ResultSet resultSet) {
        T event = adapter.apply(resultSet);

        try {
            jsonGen.writeObject(event);
        } catch (IOException e) {
            logger.error("Unable to write object to JsonGenerator", e);
        }
    }
}
