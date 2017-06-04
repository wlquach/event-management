package io.wquach.domain;

import java.time.LocalDateTime;

public class EventBuilder {
    private int id;
    private String title;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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

    public EventBuilder startTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public EventBuilder endTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public Event build() {
        return new Event(id, title, location, startTime, endTime);
    }
}