package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

import io.wquach.dao.Dao;
import io.wquach.dao.EventDao;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Component
public class EventService extends AbstractService<Event> {
    @Autowired
    EventDao eventDao;

    @Override
    protected Dao<Event> getDao() {
        return eventDao;
    }

    @Override
    public List<Event> getSubset(int filter) {
        throw new UnsupportedOperationException();
    }

    public List<Event> getEventsByTitle(String title) {
        return eventDao.getEventsByTitle(title);
    }

    @Override
    public void getAll(Consumer processor, Integer page) {
        eventDao.writeAll(processor, page);
    }
}
