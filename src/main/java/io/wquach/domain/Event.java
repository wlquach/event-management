package io.wquach.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.validation.Constraint;
import javax.validation.constraints.NotNull;

/**
 * Created by wquach on 6/3/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    int id;

    @NotNull
    String title;

    String location;

    @NotNull
    Date startTime;

    Date endTime;

    public Event() {}

    Event(String title, String location, Date startTime, Date endTime) {
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
