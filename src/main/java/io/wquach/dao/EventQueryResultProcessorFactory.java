package io.wquach.dao;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.Writer;
import java.sql.ResultSet;
import java.util.function.Function;

import io.wquach.dao.jdbc.EventQueryResultProcessor;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
public interface EventQueryResultProcessorFactory {
    EventQueryResultProcessor get(JsonGenerator jsonGen);
}
