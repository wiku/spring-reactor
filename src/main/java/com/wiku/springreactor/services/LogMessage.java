package com.wiku.springreactor.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogMessage
{
    private String message;
    private long timestamp;
    private String randomString;
}
