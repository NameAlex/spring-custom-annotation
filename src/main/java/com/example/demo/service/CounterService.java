package com.example.demo.service;

import com.example.demo.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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

    @Pointcut("@annotation(com.example.demo.annotation.Log)")
    private void logOperation() {
    }

    @Pointcut("@within(com.example.demo.annotation.Log) && execution(public * *(..))")
    public void publicLogOperation() {
    }


    @Around("logOperation() || publicLogOperation()")
    public Object executeWithAop(ProceedingJoinPoint pjp) throws Throwable {
        Log annotation = pjp.getTarget().getClass().getAnnotation(Log.class);
        if (annotation == null) {
            annotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(Log.class);
        }
        log.info(annotation.value() + ":before");
        try {
            return pjp.proceed();
        } finally {
            log.info(annotation.value() + ":after");
        }
    }

    public int getCount() {
        return counter.get();
    }
}
