package io.wquach.dao.jdbc.query;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Created by wquach on 6/3/17.
 */
public interface QueryResultProcessorFactory<T> {
    QueryResultProcessor<T> get(JsonGenerator jsonGen);
}
