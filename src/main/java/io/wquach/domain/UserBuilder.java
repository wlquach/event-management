package io.wquach.domain;

public class UserBuilder {
    private int id;
    private String username;
    private String firstName;
    private String lastName;

    public static UserBuilder create() {
        return new UserBuilder();
    }

    public UserBuilder id(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User build() {
        return new User(id, username, firstName, lastName);
    }
}