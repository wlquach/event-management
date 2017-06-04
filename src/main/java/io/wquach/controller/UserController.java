package io.wquach.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@RestController
@RequestMapping(path = "/v1/users")
public class UserController {
    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = "application/json", produces = "application/json")
    public User addUser(@Valid @RequestBody User user) {
        return null;
    }
}
