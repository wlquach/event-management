package io.wquach.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by wquach on 6/3/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Identifiable{
    int id;

    @NotNull
    @Length(max = 64)
    String username;

    @NotNull
    @Length(max = 32)
    String firstName;

    @NotNull
    @Length(max = 32)
    String lastName;

    public User(){}

    User(int id, String username, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
