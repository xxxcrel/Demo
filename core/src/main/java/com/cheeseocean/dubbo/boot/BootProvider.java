package com.cheeseocean.dubbo.boot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableDubboConfig
public class BootProvider {
    public static void main(String[] args) {
        SpringApplication.run(BootProvider.class, args);
    }
}
