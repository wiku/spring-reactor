package com.wiku.springreactor.events;

import com.wiku.springreactor.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController public class EventsRestController
{

    @Autowired private MessageTransformingService logMessageGenerator;

    @Autowired private DiskWritingService diskWritingService;

    @Autowired private AsyncTaskWrapper taskWrapper;

    @Autowired private RandomNumberGeneratingService randomNumberGenerator;

    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public void sendEvent( @RequestBody EventMessage event )
    {
        Mono<LogMessage> transform1 = getLogMessageWithText(event);
        Mono<LogMessage> transform2 = getRandomNumberLogMessage(event);
        Mono.zip(transform1, transform2)
                .subscribe(results -> diskWritingService.writeToDisk(
                        results.getT1().getTimestamp() + " | " + results.getT1().getMessage() + " | " + results.getT2()
                                .getMessage() + System.lineSeparator()));
    }

    private Mono<LogMessage> getLogMessageWithText( @RequestBody EventMessage event )
    {
        return taskWrapper.async(() -> logMessageGenerator.handleMessage(event.getText()));
    }

    private Mono<LogMessage> getRandomNumberLogMessage( @RequestBody EventMessage event )
    {
        return taskWrapper.async(() -> new LogMessage(
                event.getText() + " " + String.valueOf(randomNumberGenerator.generateRandom(10000)),
                System.currentTimeMillis()));
    }

}
