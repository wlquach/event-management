package io.wquach.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import io.wquach.dao.jdbc.adapter.InvitationResultSetExtractor;
import io.wquach.dao.jdbc.adapter.InvitationRowMapper;
import io.wquach.domain.Invitation;

/**
 * Created by wquach on 6/4/17.
 */
@Repository
@ConfigurationProperties(prefix = "dao.invitation")
public class JdbcInvitationDao extends AbstractJdbcDao<Invitation> {
    @Autowired
    private InvitationRowMapper adapter;

    @Autowired
    private InvitationResultSetExtractor rsExtractor;

    @Override
    public int insert(Invitation invitation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pStmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            pStmt.setInt(++i, invitation.getEventId());
            pStmt.setInt(++i, invitation.getUserId());
            pStmt.setBoolean(++i, invitation.isAccepted());
            return pStmt;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Invitation invitation) {
        jdbcTemplate.update(updateQuery, invitation.isAccepted(), invitation.getId());
    }

    public Invitation getInvitationsByEvent(int eventId) {
        return jdbcTemplate.query(selectAllQuery, rsExtractor, eventId);
    }

    @Override
    protected RowMapper<Invitation> getRowMapper() {
        return adapter;
    }
}
