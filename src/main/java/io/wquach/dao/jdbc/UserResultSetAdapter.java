package io.wquach.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

import io.wquach.domain.Event;
import io.wquach.domain.EventBuilder;
import io.wquach.domain.User;
import io.wquach.domain.UserBuilder;

/**
 * Created by wquach on 6/3/17.
 */
@Component
@Qualifier("jdbcUser")
public class UserResultSetAdapter implements Function<ResultSet, User>, RowMapper<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserResultSetAdapter.class);
    private static final String ID_COLUMN_NAME = "id";
    private static final String USERNAME_COLUMN_NAME = "username";
    private static final String FIRSTNAME_COLUMN_NAME = "firstName";
    private static final String LASTNAME_COLUMN_NAME = "lastName";

    public User apply(ResultSet resultSet) {
        try {
            return UserBuilder.create()
                    .id(resultSet.getInt(ID_COLUMN_NAME))
                    .username(resultSet.getString(USERNAME_COLUMN_NAME))
                    .firstName(resultSet.getString(FIRSTNAME_COLUMN_NAME))
                    .lastName(resultSet.getString(LASTNAME_COLUMN_NAME))
                    .build();
        } catch (SQLException e) {
            logger.error("Unable to adapt ResultSet to Event", e);
        }

        return null;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return apply(rs);
    }
}
