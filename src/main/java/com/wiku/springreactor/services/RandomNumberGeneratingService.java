package com.wiku.springreactor.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumberGeneratingService
{

    public int generateRandom(int maxValue)
    {
        Random random = new Random();
        int randomNumber = random.nextInt(maxValue);
        System.out.println(Thread.currentThread().getName() + " generated random number: " + randomNumber);
        return randomNumber;
    }
}
