package io.wquach.service;

import java.util.function.Consumer;

import io.wquach.dao.jdbc.EventQueryResultProcessor;
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

    /**
     * Get all events in the system, and processes them using the provided processor
     * @param processor used to process the results of the query
     */
    void getAllEvents(Consumer processor);

    /**
     * Get a single event from the system
     * @param eventId the ID of the event
     * @return Event object representing the event with the ID eventId
     */
    Event getSingleEvent(int eventId);

    /**
     * Delete a single event from the system
     * @param eventId the ID of the event to delete
     */
    void deleteSingleEvent(int eventId);
}
