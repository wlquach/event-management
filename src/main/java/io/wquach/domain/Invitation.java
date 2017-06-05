package io.wquach.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Represents an Invitation to a User for an Event
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invitation implements Identifiable{
    int id;

    Integer eventId;

    @NotNull
    Integer userId;

    Event event;

    List<InvitedUser> users;

    Boolean accepted;

    public Invitation(){}

    Invitation(int id, Integer eventId, Event event, Integer userId, List<InvitedUser> users, Boolean accepted) {
        this.id = id;
        this.eventId = eventId;
        this.event = event;
        this.userId = userId;
        this.users = users;
        this.accepted = accepted;
    }

    @Override
    public int getId() {
        return id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Event getEvent() {
        return event;
    }

    public List<InvitedUser> getUsers() {
        return users;
    }

    public Boolean isAccepted() {
        return accepted;
    }
}
