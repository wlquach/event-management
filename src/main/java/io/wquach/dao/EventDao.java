package io.wquach.dao;

import java.util.List;

import io.wquach.dao.Dao;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/4/17.
 */
public interface EventDao extends Dao<Event> {
    List<Event> getEventsByTitle(String title);
}
