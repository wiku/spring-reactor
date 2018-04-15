package com.wiku.springreactor.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageTransformingService
{


    public LogMessage handleMessage(String message)
    {
        System.out.println("First service received message: " + message);
        sleep(1000l);
        LogMessage firstServiceResult = new LogMessage(message,
                System.currentTimeMillis(),
                UUID.randomUUID().toString());
        System.out.println("First service returning result " + firstServiceResult);
        return firstServiceResult;

    }

    private void sleep(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
}
