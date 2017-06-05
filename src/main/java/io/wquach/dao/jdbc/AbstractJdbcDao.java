package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.function.Consumer;

import javax.sql.DataSource;

import io.wquach.dao.Dao;
import io.wquach.domain.Identifiable;

/**
 * Created by wquach on 6/4/17.
 */
public abstract class AbstractJdbcDao<T extends Identifiable> implements Dao<T> {
    protected String insertQuery;
    protected String selectAllQuery;
    protected String selectOneQuery;
    protected String deleteOneQuery;
    protected String updateQuery;

    protected JdbcTemplate jdbcTemplate;

    protected abstract RowMapper<T> getRowMapper();

    @Autowired
    @Qualifier("primaryDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void delete(int id) {
        jdbcTemplate.update(deleteOneQuery, id);
    }

    @Override
    public void delete(T object) {
        delete(object.getId());
    }

    @Override
    public T getSingle(int id) {
        return jdbcTemplate.queryForObject(selectOneQuery, getRowMapper(), id);
    }

    @Override
    public void writeAll(Consumer processor) {
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

    public void setDeleteOneQuery(String deleteOneQuery) {
        this.deleteOneQuery = deleteOneQuery;
    }

    public void setUpdateQuery(String updateQuery) {
        this.updateQuery = updateQuery;
    }
}
