package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import io.wquach.dao.EventDao;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Component
public class EventManagementServiceImpl implements EventManagementService {
    @Autowired
    EventDao eventDao;

    @Override
    public int addEvent(Event event) {
        return eventDao.insertEvent(event);
    }

    @Override
    public void updateEvent(Event event) {
        eventDao.updateEvent(event);
    }

    @Override
    public void getAllEvents(Consumer processor) {
        eventDao.writeAllEvents(processor);
    }

    @Override
    public Event getSingleEvent(int eventId) {
        return eventDao.getSingleEvent(eventId);
    }

    @Override
    public void deleteSingleEvent(int eventId) {
        eventDao.deleteEvent(eventId);
    }
}
