package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("fileEventLogger")
public class FileEventLogger implements EventLogger{


    private String fileName;
    private File file;

    public FileEventLogger(@Value("${fileLog}")String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void logEvent(Event event) {

        try {
            if(file == null)
            FileUtils.writeStringToFile(file, event.getMsg()+"\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException{
        this.file = new File(fileName);
        file.canWrite();
    }
}
