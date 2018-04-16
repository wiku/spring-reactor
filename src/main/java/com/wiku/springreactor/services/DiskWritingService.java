package com.wiku.springreactor.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class DiskWritingService
{
    private final String OUTPUT_PATH = "spring-reactor.log";

    public void writeToDisk(String stringToWrite)
    {
        try
        {
            Files.write(Paths.get(OUTPUT_PATH), stringToWrite.getBytes("UTF-8"), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            System.out.println(Thread.currentThread().getName() +": New line written to logfile: " + stringToWrite);
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}
