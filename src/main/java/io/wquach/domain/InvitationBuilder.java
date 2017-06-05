package io.wquach.domain;

import java.util.List;

public class InvitationBuilder {
    private int id;
    private Integer eventId;
    private Event event;
    private Integer userId;
    private List<InvitedUser> users;
    private Boolean accepted;

    public static InvitationBuilder create() {
        return new InvitationBuilder();
    }

    public InvitationBuilder id(int id) {
        this.id = id;
        return this;
    }

    public InvitationBuilder eventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public InvitationBuilder event(Event event) {
        this.event = event;
        return this;
    }

    public InvitationBuilder userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public InvitationBuilder users(List<InvitedUser> users) {
        this.users = users;
        return this;
    }

    public InvitationBuilder accepted(Boolean accepted) {
        this.accepted = accepted;
        return this;
    }

    public Invitation build() {
        return new Invitation(id, eventId, event, userId, users, accepted);
    }
}