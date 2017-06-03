package io.wquach.service;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
public interface EventManagementService {

    /**
     * Add an event to the system
     * @param event the event to add
     * @return the id of the event in the system
     */
    int addEvent(Event event);
}
