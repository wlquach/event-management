package io.wquach.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.wquach.dao.EventQueryResultProcessorFactory;
import io.wquach.domain.Event;
import io.wquach.domain.EventBuilder;
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

    @Autowired
    JsonFactory jsonFactory;

    @RequestMapping(method = RequestMethod.POST, path = "/events", consumes = "application/json", produces = "application/json")
    public Event postEvent(@Valid @RequestBody Event event) {
        //if event comes with ID throw
        int id = service.addEvent(event);

        return EventBuilder.create()
                .id(id)
                .title(event.getTitle())
                .location(event.getLocation())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();
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

        JsonGenerator jsonGen = jsonFactory.createGenerator(responseWriter);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonGen.setCodec(objectMapper);
        jsonGen.writeStartArray();

        Consumer queryResultProcessor = eventQueryResultProcessorFactory.get(jsonGen);
        service.getAllEvents(queryResultProcessor);

        jsonGen.writeEndArray();
        jsonGen.flush();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/events/{id}", produces = "application/json")
    public Event getSingleEvent(@PathVariable int id) {
        return service.getSingleEvent(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/events/{id}")
    public ResponseEntity deleteSingleEvent(@PathVariable int id) {
        service.deleteSingleEvent(id);
        return ResponseEntity.noContent().build();
    }
}
