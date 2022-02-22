package com.cheeseocean.dubbo.consumer;

import com.cheeseocean.dubbo.provider.DemoService;

public class CustomDemoServiceStub implements DemoService {

    private DemoService demoService;
    public CustomDemoServiceStub(DemoService demoService){
        this.demoService = demoService;
    }
    @Override
    public String sayHello(String name) {
        System.out.println("consumer: before sayHello");
        return demoService.sayHello("hell");
    }

    public void onConnect(){
        System.out.println("Consumer sayHello");
    }
}
