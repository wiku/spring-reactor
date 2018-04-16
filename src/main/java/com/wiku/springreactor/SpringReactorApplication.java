package com.wiku.springreactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan
@Configuration
public class SpringReactorApplication
{

    public static void main( String[] args )
    {
        SpringApplication.run(SpringReactorApplication.class, args);
    }

    @Bean
    public Scheduler asyncScheduler()
    {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(128));
    }

}
