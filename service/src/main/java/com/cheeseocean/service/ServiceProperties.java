package com.cheeseocean.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("beer.cheese.service")
public class ServiceProperties {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
