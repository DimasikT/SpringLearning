package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

/**
 * Created by Admin on 27.12.2016.
 */
public interface EventLogger {
    void logEvent(Event event);
}
