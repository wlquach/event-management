package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import io.wquach.dao.jdbc.adapter.InvitationResultSetAdapter;
import io.wquach.dao.jdbc.adapter.UserResultSetAdapter;
import io.wquach.domain.Invitation;
import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@Repository
@ConfigurationProperties(prefix = "dao.invitedUser")
@Qualifier("invitedUser")
public class JdbcInvitedUserDao extends AbstractJdbcDao<User> {
    @Autowired
    private UserResultSetAdapter adapter;

    @Override
    public int insert(User user) {
       throw new UnsupportedOperationException();
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getSubset(int eventId) {
        return jdbcTemplate.query(selectAllQuery, adapter, eventId);
    }

    @Override
    protected RowMapper<User> getRowMapper() {
        return adapter;
    }
}
