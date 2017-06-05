package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@Repository
@ConfigurationProperties(prefix = "dao.user")
public class JdbcUserDao extends AbstractJdbcDao<User> {
    @Autowired
    private UserResultSetAdapter adapter;

    @Autowired
    @Qualifier("primaryDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pStmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            pStmt.setString(++i, user.getUsername());
            pStmt.setString(++i, user.getFirstName());
            pStmt.setString(++i, user.getLastName());
            return pStmt;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(updateQuery, user.getUsername(), user.getFirstName(), user.getLastName(), user.getId());
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return adapter;
    }
}
