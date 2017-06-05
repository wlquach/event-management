package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Repository
@ConfigurationProperties(prefix = "dao.event")
public class JdbcEventDao extends AbstractJdbcDao<Event> {
    @Autowired
    private EventResultSetAdapter adapter;

    @Override
    public int insert(Event event) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pStmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            pStmt.setString(++i, event.getTitle());
            pStmt.setString(++i, event.getLocation());
            pStmt.setTimestamp(++i, Timestamp.valueOf(event.getStartTime()));
            pStmt.setTimestamp(++i, Timestamp.valueOf(event.getEndTime()));
            return pStmt;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Event event) {
        jdbcTemplate.update(updateQuery, event.getTitle(), event.getLocation(),
                event.getStartTime(), event.getEndTime(), event.getId());
    }

    @Override
    protected RowMapper<Event> getRowMapper() {
        return adapter;
    }
}
