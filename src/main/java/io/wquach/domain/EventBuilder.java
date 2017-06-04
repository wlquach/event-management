package io.wquach.domain;

import java.util.Date;

public class EventBuilder {
    private int id;
    private String title;
    private String location;
    private Date startTime;
    private Date endTime;

    public static EventBuilder create() {
        return new EventBuilder();
    }

    public EventBuilder id(int id) {
        this.id = id;
        return this;
    }

    public EventBuilder title(String title) {
        this.title = title;
        return this;
    }

    public EventBuilder location(String location) {
        this.location = location;
        return this;
    }

    public EventBuilder startTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public EventBuilder endTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Event build() {
        return new Event(title, location, startTime, endTime);
    }
}