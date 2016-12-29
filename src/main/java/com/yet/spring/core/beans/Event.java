package com.yet.spring.core.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;


@Component
@Scope("prototype")
public class Event {

    private static int count = 0;

    private int id;

    @Value("Some event for user 1")
    private String msg;

    @Autowired
    @Qualifier("date")
    private Date date;

//    @Autowired
//    @Qualifier("dateFormat")
    @Value("#{T(java.text.DateFormat).getDateTimeInstance()}")
    private DateFormat df;


    public Event() {
        this.id = count++;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDf(DateFormat df) {
        this.df = df;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
