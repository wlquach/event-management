package io.wquach.dao.jdbc.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.wquach.domain.Event;
import io.wquach.domain.EventBuilder;

/**
 * Created by wquach on 6/3/17.
 */
@Component
@Qualifier("jdbcEvent")
public class EventResultSetAdapter extends AbstractResultSetAdapter<Event> {
    private static final Logger logger = LoggerFactory.getLogger(EventResultSetAdapter.class);
    private static final String ID_COLUMN_NAME = "id";
    private static final String TITLE_COLUMN_NAME = "title";
    private static final String LOCATION_COLUMN_NAME = "location";
    private static final String START_DATE_COLUMN_NAME = "startTime";
    private static final String END_DATE_COLUMN_NAME = "endTime";

    public Event apply(ResultSet resultSet) {
        try {
            return EventBuilder.create()
                    .id(resultSet.getInt(ID_COLUMN_NAME))
                    .title(resultSet.getString(TITLE_COLUMN_NAME))
                    .location(resultSet.getString(LOCATION_COLUMN_NAME))
                    .startTime(resultSet.getTimestamp(START_DATE_COLUMN_NAME).toLocalDateTime())
                    .endTime(resultSet.getTimestamp(END_DATE_COLUMN_NAME).toLocalDateTime())
                    .build();
        } catch (SQLException e) {
            logger.error("Unable to adapt ResultSet to Event", e);
        }

        return null;
    }
}
