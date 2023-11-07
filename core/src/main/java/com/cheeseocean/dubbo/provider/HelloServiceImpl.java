package com.cheeseocean.dubbo.provider;

public class HelloServiceImpl implements HelloService{
    @Override
    public void greeting() {
        System.out.println("hello");
    }
}
