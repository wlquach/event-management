package io.wquach.dao.jdbc.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.wquach.domain.User;
import io.wquach.domain.UserBuilder;

/**
 * Created by wquach on 6/3/17.
 */
@Component
@Qualifier("jdbcUser")
public class UserRowMapper extends AbstractRowMapper<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserRowMapper.class);
    static final String ID_COLUMN_NAME = "userId";
    static final String USERNAME_COLUMN_NAME = "username";
    static final String FIRSTNAME_COLUMN_NAME = "firstName";
    static final String LASTNAME_COLUMN_NAME = "lastName";

    public User apply(ResultSet resultSet) {
        try {
            return UserBuilder.create()
                    .id(resultSet.getInt(ID_COLUMN_NAME))
                    .username(resultSet.getString(USERNAME_COLUMN_NAME))
                    .firstName(resultSet.getString(FIRSTNAME_COLUMN_NAME))
                    .lastName(resultSet.getString(LASTNAME_COLUMN_NAME))
                    .build();
        } catch (SQLException e) {
            logger.error("Unable to adapt ResultSet to User", e);
        }

        return null;
    }
}
