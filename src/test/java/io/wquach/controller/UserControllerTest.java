package io.wquach.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import io.wquach.dao.jdbc.query.UserQueryResultProcessorFactory;
import io.wquach.domain.Event;
import io.wquach.domain.EventBuilder;
import io.wquach.domain.User;
import io.wquach.domain.UserBuilder;
import io.wquach.service.CrudService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    UserQueryResultProcessorFactory mockQueryResultProcessorFactory;

    @MockBean
    @Qualifier("user")
    CrudService<User> mockUserService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void happyPath() throws Exception {
        String body = getRequestBody("mmouse", "Mickey", "Mouse");

        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(content().json(body));
    }

    @Test
    public void testMissingUsername() throws Exception {
        String body = getRequestBody("", "Mickey", "Mouse");

        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMissingFirstName() throws Exception {
        String body = getRequestBody("mmouse", "", "Mouse");

        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMissingLastName() throws Exception {
        String body = getRequestBody("mmouse", "Mickey", "");

        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUsernameTooLong() throws Exception {
        String body = getRequestBody("mmousemmousemmousemmousemmousemmo", "Mickey", "Mouse");

        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFirstNameTooLong() throws Exception {
        String body = getRequestBody("mmouse", "MickeyMickeyMickeyMickeyMickeyMIc", "Mouse");

        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testLastNameTooLong() throws Exception {
        String body = getRequestBody("mmouse", "Mickey", "MouseMouseMouseMouseMouseMouseMou");

        this.mvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON).content(body).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private String getRequestBody(String username, String firstName, String lastName) throws JsonProcessingException {
        User user = UserBuilder.create()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return mapper.writeValueAsString(user);
    }
}
