package com.wiku.springreactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan
@Configuration
public class SpringReactorApplication
{


    public static void main( String[] args )
    {
        SpringApplication.run(SpringReactorApplication.class, args);
    }


}
