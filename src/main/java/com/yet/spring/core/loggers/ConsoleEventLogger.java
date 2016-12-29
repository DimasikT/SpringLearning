package com.yet.spring.core.loggers;


import com.yet.spring.core.beans.Event;
import org.springframework.stereotype.Component;

@Component("consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {

    public ConsoleEventLogger() {

    }

    public void logEvent(Event event){
        System.out.println(event);
    }
}
