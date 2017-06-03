package io.wquach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.wquach.dao.EventDao;
import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@RestController
public class EventController {
    @Autowired
    EventDao dao;

    @RequestMapping(method = RequestMethod.POST, path = "/events", consumes = "application/json", produces = "application/json")
    public Event postEvent(@Valid @RequestBody Event event) {
        dao.testQuery();
        return event;
    }
}
