package io.wquach.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.wquach.dao.jdbc.query.QueryResultProcessorFactory;
import io.wquach.domain.User;
import io.wquach.domain.UserBuilder;
import io.wquach.service.CrudService;

/**
 * Created by wquach on 6/4/17.
 */
@RestController
@RequestMapping(path = "/v1/users")
public class UserController {
    @Autowired
    @Qualifier("jdbcUser")
    QueryResultProcessorFactory queryResultProcessorFactory;

    @Autowired
    @Qualifier("user")
    CrudService<User> userService;

    @Autowired
    JsonFactory jsonFactory;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        //if event comes with ID throw
        int id = userService.add(user);

        User newUser = UserBuilder.create()
                .id(id)
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
        URI resource = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(resource).body(newUser);
    }

    /**
     * Get all the users in the system. For scalability, this method will write the users to the writer as they are being
     * queried from persistence.
     *
     * @param responseWriter the response writer to write the users to
     * @param response       the HttpServletResponse used to set headers
     * @throws IOException if writing to the Writer fails
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void getAllUsers(Writer responseWriter, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        JsonGenerator jsonGen = jsonFactory.createGenerator(responseWriter);
        ObjectMapper objectMapper = new ObjectMapper();
        jsonGen.setCodec(objectMapper);
        jsonGen.writeStartArray();

        Consumer queryResultProcessor = queryResultProcessorFactory.get(jsonGen);
        userService.getAll(queryResultProcessor);

        jsonGen.writeEndArray();
        jsonGen.flush();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
    public User getSingleUser(@PathVariable int id) {
        return userService.getOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity deleteSingleUser(@PathVariable int id) {
        userService.deleteSingle(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        User updateUser = UserBuilder.create()
                .id(id)
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
        userService.update(updateUser);
        return ResponseEntity.noContent().build();
    }
}
