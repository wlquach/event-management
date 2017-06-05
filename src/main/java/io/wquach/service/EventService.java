package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import io.wquach.dao.Dao;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Component
public class EventService implements CrudService<Event> {
    @Autowired
    Dao<Event> eventDao;

    @Override
    public int add(Event event) {
        return eventDao.insert(event);
    }

    @Override
    public void update(Event event) {
        eventDao.update(event);
    }

    @Override
    public void getAll(Consumer processor) {
        eventDao.writeAll(processor);
    }

    @Override
    public Event getSingle(int eventId) {
        return eventDao.getSingle(eventId);
    }

    @Override
    public void deleteSingle(int eventId) {
        eventDao.delete(eventId);
    }
}
