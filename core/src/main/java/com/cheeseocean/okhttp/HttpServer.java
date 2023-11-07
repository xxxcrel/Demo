package com.cheeseocean.okhttp;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/hello")
@RestController
public class HttpServer {
    public static void main(String[] args) {
        SpringApplication.run(HttpServer.class);
    }

    @GetMapping
    public String hello(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    @PostMapping("/add")
    public String add(){
        System.out.println("hello/add");
        return "hello";
    }

    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .anyRequest().permitAll();
    }
}
