package io.wquach.dao.jdbc.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import io.wquach.domain.Event;
import io.wquach.domain.EventBuilder;
import io.wquach.domain.Invitation;
import io.wquach.domain.InvitationBuilder;
import io.wquach.domain.InvitedUser;
import io.wquach.domain.User;
import io.wquach.domain.UserBuilder;

/**
 * Created by wquach on 6/3/17.
 */
@Component
@Qualifier("jdbcInvitation")
public class InvitationRowMapper extends AbstractRowMapper<Invitation> {
    private static final Logger logger = LoggerFactory.getLogger(InvitationRowMapper.class);
    static final String USER_ID_COLUMN_NAME = "userId";
    static final String EVENT_ID_COLUMN_NAME = "eventId";
    static final String ACCEPTED_COLUMN_NAME = "accepted";

    @Autowired
    EventRowMapper eventRowMapper;

    @Autowired
    UserRowMapper userRowMapper;

    public Invitation apply(ResultSet resultSet) {
        try {
            //get Event
            Event event = eventRowMapper.apply(resultSet);

            //get User
            User user = userRowMapper.apply(resultSet);
            InvitedUser invitedUser = new InvitedUser(user, resultSet.getBoolean(ACCEPTED_COLUMN_NAME));

            return InvitationBuilder.create()
                    .eventId(resultSet.getInt(EVENT_ID_COLUMN_NAME))
                    .event(event)
                    .userId(resultSet.getInt(USER_ID_COLUMN_NAME))
                    .users(Collections.singletonList(invitedUser))
                    .build();
        } catch (SQLException e) {
            logger.error("Unable to adapt ResultSet to Invitation", e);
        }

        return null;
    }
}
