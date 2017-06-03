package io.wquach.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import io.wquach.domain.Event;

/**
 * Created by wquach on 6/3/17.
 */
@Component
public class EventManagementServiceImpl implements EventManagementService {
    @Override
    public int addEvent(@Valid Event event) {
        return 0;
    }
}
