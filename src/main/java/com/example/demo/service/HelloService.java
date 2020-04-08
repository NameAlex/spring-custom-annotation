package com.example.demo.service;

import com.example.demo.annotation.Count;
import com.example.demo.annotation.Log;
import org.springframework.stereotype.Service;
import static com.example.demo.model.LogType.LOGBACK;

@Service
public class HelloService {

    @Log(LOGBACK)
    @Count
    public String hello(String name) {
        return "Hello, " + name;
    }
}
