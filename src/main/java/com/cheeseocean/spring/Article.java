package com.cheeseocean.spring;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class Article {
    @Resource(name = "content")
    Text text;

    public Article(){

    }

    public void print(){
        System.out.println("content: " + text);
    }
}
