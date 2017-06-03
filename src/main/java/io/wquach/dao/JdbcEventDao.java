package io.wquach.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by wquach on 6/3/17.
 */
@Repository
public class JdbcEventDao implements EventDao{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("primaryDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void testQuery() {
        jdbcTemplate.query("SELECT 1", resultSet -> {resultSet.next();
            System.out.println(resultSet.getInt(1));});
    }
}
