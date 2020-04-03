package com.example.demo.service;

import com.example.demo.annotation.Count;
import com.example.demo.annotation.Log;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Log
    @Count
    public String hello(String name) {
        return "Hello, " + name;
    }
}
