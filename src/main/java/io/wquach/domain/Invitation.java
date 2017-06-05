package io.wquach.domain;

import javax.validation.constraints.NotNull;

/**
 * Represents an Invitation to a User for an Event
 */
public class Invitation implements Identifiable{
    int id;
    int eventId;

    @NotNull
    int userId;

    boolean accepted;

    public Invitation(){}

    Invitation(int id, int eventId, int userId, boolean accepted) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.accepted = accepted;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
