package io.wquach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.wquach.dao.EventDao;
import io.wquach.dao.EventQueryResultProcessorFactory;
import io.wquach.dao.jdbc.EventQueryResultProcessor;
import io.wquach.dao.jdbc.EventResultSetAdapter;
import io.wquach.domain.Event;
import io.wquach.service.EventManagementService;

/**
 * Created by wquach on 6/3/17.
 */
@RestController
@RequestMapping(path = "/v1")
public class EventController {
    @Autowired
    @Qualifier("jdbc")
    EventQueryResultProcessorFactory eventQueryResultProcessorFactory;

    @Autowired
    EventManagementService service;

    @RequestMapping(method = RequestMethod.POST, path = "/events", consumes = "application/json", produces = "application/json")
    public Event postEvent(@Valid @RequestBody Event event) {
        return event;
    }

    /**
     * Get all the events in the system. For scalability, this method will write the events to the writer as they are being
     * queried from persistence.
     *
     * @param responseWriter the response writer to write the events to
     * @param response       the HttpServletResponse used to set headers
     * @throws IOException if writing to the Writer fails
     */
    @RequestMapping(method = RequestMethod.GET, path = "/events", produces = "application/json")
    public void getAllEvents(Writer responseWriter, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Consumer queryResultProcessor = eventQueryResultProcessorFactory.get(responseWriter);
        service.getAllEvents(queryResultProcessor);
        responseWriter.flush();
    }
}
