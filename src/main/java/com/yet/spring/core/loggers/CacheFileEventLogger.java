package com.yet.spring.core.loggers;


import com.yet.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component("cacheFileEventLogger")
public class CacheFileEventLogger extends FileEventLogger {


    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(@Value("${fileLog}") String fileName, @Value("10")int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>();
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size() == cacheSize){
            cache.forEach(super::logEvent);
            cache.clear();
        }
    }

    @PreDestroy
    public void destroy(){
        if(!cache.isEmpty()){
            cache.forEach(super::logEvent);
        }
    }
}
