package com.example.demo;

import com.example.demo.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class DemoApplication {

	private final HelloService helloService;

	public DemoApplication(HelloService helloService) {
		this.helloService = helloService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return helloService.hello(name);
	}

}
