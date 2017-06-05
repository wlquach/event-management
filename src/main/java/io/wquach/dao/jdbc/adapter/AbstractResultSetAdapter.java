package io.wquach.dao.jdbc.adapter;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
public abstract class AbstractResultSetAdapter<T> implements Function<ResultSet, T>, RowMapper<T> {
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return apply(rs);
    }
}
