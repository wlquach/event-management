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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.wquach.dao.jdbc.query.QueryResultProcessorFactory;
import io.wquach.domain.Event;
import io.wquach.domain.EventBuilder;
import io.wquach.domain.Invitation;
import io.wquach.domain.InvitationBuilder;
import io.wquach.domain.InvitationDecision;
import io.wquach.domain.User;
import io.wquach.service.CrudService;
import io.wquach.service.EventService;

/**
 * Created by wquach on 6/3/17.
 */
@RestController
@RequestMapping(path = "/v1/events")
public class EventController {
    @Autowired
    @Qualifier("jdbcEvent")
    QueryResultProcessorFactory queryResultProcessorFactory;

    @Autowired
    EventService eventService;

    @Autowired
    CrudService<Invitation> invitationService;

    @Autowired
    @Qualifier("invitedUser")
    CrudService<User> invitedUserService;

    @Autowired
    JsonFactory jsonFactory;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity addEvent(@Valid @RequestBody Event event) {
        //if event comes with ID throw
        int id = eventService.add(event);

        Event newEvent = EventBuilder.create()
                .id(id)
                .title(event.getTitle())
                .location(event.getLocation())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();

        URI resource = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(resource).body(newEvent);
    }

    /**
     * Get all the events in the system. For scalability, this method will write the events to the writer as they are being
     * queried from persistence.
     *
     * @param responseWriter the response writer to write the events to
     * @param response       the HttpServletResponse used to set headers
     * @param titleKeyword   the keyword to search titles by
     * @throws IOException if writing to the Writer fails
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void getAllEvents(Writer responseWriter, HttpServletResponse response,
                             @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String titleKeyword) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        JsonGenerator jsonGen = jsonFactory.createGenerator(responseWriter);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonGen.setCodec(objectMapper);

        if(titleKeyword == null) {
            jsonGen.writeStartArray();
            Consumer queryResultProcessor = queryResultProcessorFactory.get(jsonGen);
            eventService.getAll(queryResultProcessor, page);
            jsonGen.writeEndArray();
        } else {
            jsonGen.writeObject(eventService.getEventsByTitle(titleKeyword));
        }

        jsonGen.flush();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
    public Event getSingleEvent(@PathVariable int id) {
        return eventService.getOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity deleteSingleEvent(@PathVariable int id) {
        eventService.deleteSingle(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity updateEvent(@PathVariable int id, @Valid @RequestBody Event event) {
        Event updateEvent = EventBuilder.create()
                .id(id)
                .title(event.getTitle())
                .location(event.getLocation())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();

        eventService.update(updateEvent);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{eventId}/invitations", consumes = "application/json", produces = "application/json")
    public ResponseEntity invite(@PathVariable int eventId,
            @Valid @RequestBody Invitation invitation) {
        //if event comes with ID throw
        int id = invitationService.add(invitation);

        Invitation newInvitation = InvitationBuilder.create()
                .id(id)
                .eventId(eventId)
                .userId(invitation.getUserId())
                .accepted(invitation.isAccepted())
                .build();
        URI resource = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(resource).body(newInvitation);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{eventId}/invitations/{id}", consumes = "application/json")
    public ResponseEntity acceptOrDecline(@PathVariable int eventId, @PathVariable int id, @RequestBody InvitationDecision decision) {
        Invitation updateInvitation = InvitationBuilder.create()
                .id(id)
                .eventId(eventId)
                .accepted(decision.isAccept())
                .build();
        invitationService.update(updateInvitation);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{eventId}/invitations", consumes = "application/json")
    public ResponseEntity getAllInvitationsForEvent(@PathVariable int eventId) {
        List<User> invitations = invitedUserService.getSubset(eventId);
        return ResponseEntity.ok().body(invitations);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{eventId}/invitations/{id}", consumes = "application/json")
    public ResponseEntity getInvitation(@PathVariable int eventId,
                                        @PathVariable int id) {
        User invitedUser = invitedUserService.getOne(id);
        return ResponseEntity.ok().body(invitedUser);
    }
}
