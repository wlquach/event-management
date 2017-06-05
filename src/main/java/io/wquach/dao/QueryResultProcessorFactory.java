package io.wquach.dao;

import com.fasterxml.jackson.core.JsonGenerator;

import io.wquach.dao.jdbc.QueryResultProcessor;

/**
 * Created by wquach on 6/3/17.
 */
public interface QueryResultProcessorFactory {
    QueryResultProcessor get(JsonGenerator jsonGen);
}
