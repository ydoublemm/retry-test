package org.example.service;

import org.example.Task.RetryTask;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Created by caojidasabi on 2020/6/11.
 */
@Service
public class RetryService {


    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 2000L, multiplier = 2))
    public Boolean call(){
        return RetryTask.retryTask("abc");
    }



}
