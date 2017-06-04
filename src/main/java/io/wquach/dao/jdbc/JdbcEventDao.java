package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
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
    private JdbcTemplate jdbcTemplate;

    private String insertQuery;
    private String selectAllQuery;

    @Autowired
    @Qualifier("primaryDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insertEvent(Event event) {
        return 0;
    }

    @Override
    public void deleteEvent(int id) {

    }

    @Override
    public void updateEvent(Event event) {

    }

    @Override
    public Event getSingleEvent(int id) {
        return null;
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
}
