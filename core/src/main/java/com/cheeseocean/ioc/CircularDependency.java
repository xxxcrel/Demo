package com.cheeseocean.ioc;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@ComponentScan
public class CircularDependency {

    public static void main(String[] args) {
        System.setProperty("test.a", "/sysenv/");
        ApplicationContext context = new AnnotationConfigApplicationContext(CircularDependency.class);
        C c = context.getBean(C.class);
        System.out.println(c.getB());
//        A a = context.getBean(A.class);
//        B b = a.takeB();
//        Assert.state(a.equals(b.takeA()), "Not equals");
    }
}

class Constants {
    @Getter
    private static String C = "/home/";

}

@Component
class C {
    @Value("#{T(com.cheeseocean.ioc.Constants).getC() + '${test.a:/1,/2,/3}'.replaceAll(',', ',' + T(com.cheeseocean.ioc.Constants).getC()).split(',')}}")
    private String[] a;

    @Value("#{T(com.cheeseocean.ioc.Constants).getC() + '${test.a:/1,/2,/3}'}")
    private String b;

    public String getB() {
        return b;
    }

    public String[] getA() {
        return a;
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