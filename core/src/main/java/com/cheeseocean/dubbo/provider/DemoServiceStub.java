package com.cheeseocean.dubbo.provider;

public class DemoServiceStub implements DemoService{

    private DemoService demoService;
    public DemoServiceStub(DemoService demoService){
        this.demoService = demoService;
    }
    @Override
    public String sayHello(String name) {
        System.out.println("before demoService");
        try {
            return demoService.sayHello(name);
        }catch (Exception e){
            System.out.println("catch exception");
        }
        return null;
    }

}
