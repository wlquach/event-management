package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.sql.ResultSet;
import java.util.function.Consumer;

import javax.validation.Valid;

import io.wquach.dao.EventDao;
import io.wquach.dao.jdbc.EventQueryResultProcessor;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Component
public class EventManagementServiceImpl implements EventManagementService {
    @Autowired
    EventDao eventDao;

    @Override
    public int addEvent(@Valid Event event) {
        return 0;
    }

    @Override
    public void getAllEvents(Consumer processor) {
        eventDao.writeAllEvents(processor);
    }

}
