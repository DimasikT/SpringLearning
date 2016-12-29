package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;
import com.yet.spring.core.loggers.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component("app")
public class App {

    @Autowired
    private Client client;

    @Value("#{loggerMap}")
    private Map<EventType, EventLogger> loggers;

    @Autowired
    @Qualifier("cacheFileEventLogger")
    private EventLogger defaultLogger;

    public App() {

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setLoggers(Map<EventType, EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring-app-annotation.xml");

        App app = (App)ctx.getBean("app");
        Event event = ctx.getBean(Event.class);


        app.logEvent(EventType.ERROR, event);
        app.logEvent(EventType.INFO, event);
        app.logEvent(null, event);

        ctx.close();
    }

    private void logEvent(EventType type, Event event) {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
