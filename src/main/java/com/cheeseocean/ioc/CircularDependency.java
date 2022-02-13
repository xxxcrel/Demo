package com.cheeseocean.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Configuration
@ComponentScan
public class CircularDependency {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CircularDependency.class);
        A a = context.getBean(A.class);
        B b = a.takeB();
        Assert.state(a.equals(b.takeA()), "Not equals");
    }
}

@Component
class A{

    @Autowired
    B b;
    public B takeB(){
        return b;
    }
}

@Component
class B{
    @Autowired
    A a;
    public A takeA(){
        return a;
    }
}