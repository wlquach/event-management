package io.wquach.domain;

import java.util.Date;

public class EventBuilder {
    private String title;
    private String location;
    private Date startTime;
    private Date endTime;

    public EventBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public EventBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public EventBuilder setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public EventBuilder setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public Event createEvent() {
        return new Event(title, location, startTime, endTime);
    }
}