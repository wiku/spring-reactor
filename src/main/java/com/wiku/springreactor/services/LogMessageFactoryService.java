package com.wiku.springreactor.services;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service public class LogMessageFactoryService
{

    public LogMessage createLogMessage( String message )
    {
        System.out.println(Thread.currentThread().getName() + ": First service received message: " + message);
        sleepForRandomTime();
        LogMessage firstServiceResult = new LogMessage(message, System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + ": First service returning result " + firstServiceResult);
        return firstServiceResult;

    }

    private void sleepForRandomTime()
    {
        Random random = new Random();
        int sleepTime = random.nextInt(2000);
        sleep(sleepTime);
    }

    private void sleep( long time )
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
