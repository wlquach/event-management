package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Consumer;

import javax.sql.DataSource;

import io.wquach.dao.EventDao;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Repository
@ConfigurationProperties(prefix = "dao.event")
public class JdbcEventDao implements EventDao {
    @Autowired
    private EventResultSetAdapter adapter;

    private JdbcTemplate jdbcTemplate;

    private String insertQuery;
    private String selectAllQuery;
    private String selectOneQuery;

    @Autowired
    @Qualifier("primaryDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insertEvent(Event event) {
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
    public void deleteEvent(int id) {

    }

    @Override
    public void updateEvent(Event event) {

    }

    @Override
    public Event getSingleEvent(int id) {
        return jdbcTemplate.queryForObject(selectOneQuery, adapter, id);
    }

    @Override
    public void writeAllEvents(Consumer processor) {
        jdbcTemplate.query(selectAllQuery, resultSet -> {
            processor.accept(resultSet);
        });
    }

    public void setInsertQuery(String insertQuery) {
        this.insertQuery = insertQuery;
    }

    public void setSelectAllQuery(String selectAllQuery) {
        this.selectAllQuery = selectAllQuery;
    }

    public void setSelectOneQuery(String selectOneQuery) {
        this.selectOneQuery = selectOneQuery;
    }
}
