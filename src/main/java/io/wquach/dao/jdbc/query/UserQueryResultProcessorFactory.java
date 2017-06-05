package io.wquach.dao.jdbc.query;

import com.fasterxml.jackson.core.JsonGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

import io.wquach.domain.User;

/**
 * Created by wquach on 6/3/17.
 */
@Component
@Qualifier("jdbcUser")
public class UserQueryResultProcessorFactory implements QueryResultProcessorFactory<User>{
    @Autowired
    @Qualifier("jdbcUser")
    Function<ResultSet, User> userQueryResultAdapter;

    @Override
    public QueryResultProcessor<User> get(JsonGenerator jsonGen) {
        return new QueryResultProcessor<>(jsonGen, userQueryResultAdapter::apply);
    }
}
