package com.example.demo.service;

import com.example.demo.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class CounterService {

    private AtomicInteger counter = new AtomicInteger();

    private static final Logger log = LoggerFactory.getLogger(CounterService.class);

    @Before("@annotation(com.example.demo.annotation.Count)")
    public void increment() {
        int result = counter.incrementAndGet();
        log.info(String.valueOf(result));
    }

    @Around("@annotation(logAnnotation)")
    public Object around(ProceedingJoinPoint pjp, Log logAnnotation) throws Throwable {
        log.info(logAnnotation.value() + ":before");
        try {
            return pjp.proceed();
        } finally {
            log.info(logAnnotation.value() + ":after");
        }
    }

    public int getCount() {
        return counter.get();
    }
}
