package com.yet.spring.core.loggers;


import com.yet.spring.core.beans.Event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("combinedEventLogger")
public class CombinedEventLogger implements EventLogger {


    @Value("#{combinedLogger}")
    private Collection<EventLogger> loggers;

    public CombinedEventLogger() {

    }

    public void setLoggers(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger el : loggers) {
            el.logEvent(event);
        }
    }
}
