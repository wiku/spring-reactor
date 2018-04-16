package com.wiku.springreactor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

@Service
public class AsyncTaskWrapper
{
    @Autowired
    private Scheduler asyncTaskScheduler;

    public <T> Mono<T> async(Callable<T> task)
    {
        return Mono.fromCallable(task).subscribeOn(asyncTaskScheduler).publishOn(Schedulers.parallel());
    }
}
