package com.cheeseocean.aop;

import org.springframework.stereotype.Component;

@Component
public class TargetObject {
    private static int id;
    public TargetObject(){
        id++;
    }
    public void hello(){
        System.out.println("hello im " + id);
    }
}
