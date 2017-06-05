package io.wquach.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import io.wquach.dao.jdbc.query.EventQueryResultProcessorFactory;
import io.wquach.dao.jdbc.query.UserQueryResultProcessorFactory;
import io.wquach.domain.Event;
import io.wquach.domain.EventBuilder;
import io.wquach.domain.User;
import io.wquach.service.CrudService;
import io.wquach.service.EventService;
import io.wquach.service.InvitationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {
    @MockBean
    EventQueryResultProcessorFactory mockQueryResultProcessorFactory;

    @MockBean
    EventService mockEventService;

    @MockBean
    InvitationService mockInvitationService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void happyPath() throws Exception {
        String body = getRequestBody("Test Event", "Test Location");

        this.mvc.perform(post("/v1/events").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(content().json(body));
    }

    @Test
    public void testNullEventTitle() throws Exception {
        String body = getRequestBody(null, "Test Location");

        this.mvc.perform(post("/v1/events").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNullStartTime() throws Exception {
        String body = getRequestBody("Test Event", "Test Location", null, LocalDateTime.now());

        this.mvc.perform(post("/v1/events").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTitleTooShort() throws Exception {
        String body = getRequestBody("", "Test Location");

        this.mvc.perform(post("/v1/events").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTitleTooLong() throws Exception {
        String body = getRequestBody("sixtyfivecharacterssixtyfivecharacterssixtyfivecharacterssixtyfiv", "Test Location");

        this.mvc.perform(post("/v1/events").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLocationTooLong() throws Exception {
        String body = getRequestBody("Test Title", "onehundredtwentyninecharactersonehundredtwentyninecharactersonehundredtwentyninecharactersonehundredtwentyninecharactersonehundre");

        this.mvc.perform(post("/v1/events").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private String getRequestBody(String title, String location, LocalDateTime startTime, LocalDateTime endTime) throws JsonProcessingException {
        Event event = EventBuilder.create().title(title).location(location).startTime(startTime).endTime(endTime).build();

        return mapper.writeValueAsString(event);
    }

    private String getRequestBody(String title, String location) throws JsonProcessingException {
        return getRequestBody(title, location, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
    }
}
