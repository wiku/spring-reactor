package com.wiku.springreactor;

import com.wiku.springreactor.services.DiskWritingService;
import com.wiku.springreactor.services.LogMessage;
import com.wiku.springreactor.services.MessageTransformingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController public class EventsRestController
{

    @Autowired MessageTransformingService messageTransformingService;

    @Autowired DiskWritingService diskWritingService;


    ExecutorService threadPool = Executors.newFixedThreadPool(64);

    @RequestMapping(path = "/events", method = RequestMethod.POST) public Long sendEvent( @RequestBody EventMessage event )
    {
        long timeStarted = System.currentTimeMillis();
        Mono.just(event)
                .map(EventMessage::getText)
                .map(text -> messageTransformingService.handleMessage(text)).subscribeOn(Schedulers.fromExecutor(threadPool))
                .subscribe(logMessage -> diskWritingService.writeToDisk(getStringToWrite(logMessage)));
        return System.currentTimeMillis() - timeStarted;
    }

    private String getStringToWrite( LogMessage logMessage )
    {
        return logMessage.getTimestamp() + " | " + logMessage.getMessage() + " | " + logMessage.getRandomString() + System.lineSeparator();
    }
}
