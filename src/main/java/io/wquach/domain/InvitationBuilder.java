package io.wquach.domain;

public class InvitationBuilder {
    private int id;
    private int eventId;
    private int userId;
    private boolean accepted;

    public static InvitationBuilder create() {
        return new InvitationBuilder();
    }

    public InvitationBuilder id(int id) {
        this.id = id;
        return this;
    }

    public InvitationBuilder eventId(int eventId) {
        this.eventId = eventId;
        return this;
    }

    public InvitationBuilder userId(int userId) {
        this.userId = userId;
        return this;
    }

    public InvitationBuilder accepted(boolean accepted) {
        this.accepted = accepted;
        return this;
    }

    public Invitation build() {
        return new Invitation(id, eventId, userId, accepted);
    }
}