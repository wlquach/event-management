package io.wquach.dao.jdbc.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import io.wquach.domain.Event;
import io.wquach.domain.Invitation;
import io.wquach.domain.InvitationBuilder;
import io.wquach.domain.InvitedUser;

/**
 * Created by wquach on 6/5/17.
 */
@Component
public class InvitationResultSetExtractor implements ResultSetExtractor<Invitation> {
    @Autowired
    EventRowMapper eventRowMapper;

    @Autowired
    UserRowMapper userRowMapper;

    @Override
    public Invitation extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()) {
            //events are all the same, get one using row mapper
            Event event = eventRowMapper.apply(rs);

            List<InvitedUser> users = new LinkedList<>();

            //get all the users
            do {
                InvitedUser invitedUser = new InvitedUser(userRowMapper.apply(rs), rs.getBoolean(InvitationRowMapper.ACCEPTED_COLUMN_NAME));
                users.add(invitedUser);
            } while (rs.next());

            return InvitationBuilder.create()
                    .event(event)
                    .users(users)
                    .build();
        } else {
            return null;
        }
    }
}
