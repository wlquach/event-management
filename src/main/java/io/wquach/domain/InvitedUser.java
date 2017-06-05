package io.wquach.domain;

/**
 * Created by wquach on 6/5/17.
 */
public class InvitedUser {
    User user;
    boolean accepted;

    public InvitedUser(User user, boolean accepted) {
        this.user = user;
        this.accepted = accepted;
    }

    public User getUser() {
        return user;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
