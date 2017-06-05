package io.wquach.dao.jdbc.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.wquach.domain.Invitation;
import io.wquach.domain.InvitationBuilder;

/**
 * Created by wquach on 6/3/17.
 */
@Component
@Qualifier("jdbcInvitation")
public class InvitationResultSetAdapter extends AbstractResultSetAdapter<Invitation> {
    private static final Logger logger = LoggerFactory.getLogger(InvitationResultSetAdapter.class);
    private static final String EVENT_ID_COLUMN_NAME = "eventId";
    private static final String USER_ID_COLUMN_NAME = "userId";
    private static final String ACCEPTED_COLUMN_NAME = "accepted";

    public Invitation apply(ResultSet resultSet) {
        try {
            return InvitationBuilder.create()
                    .eventId(resultSet.getInt(EVENT_ID_COLUMN_NAME))
                    .userId(resultSet.getInt(USER_ID_COLUMN_NAME))
                    .accepted(resultSet.getBoolean(ACCEPTED_COLUMN_NAME)).build();
        } catch (SQLException e) {
            logger.error("Unable to adapt ResultSet to Invitation", e);
        }

        return null;
    }
}
