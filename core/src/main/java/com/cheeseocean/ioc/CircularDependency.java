package com.cheeseocean.ioc;

import lombok.Getter;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@ComponentScan(basePackageClasses = {A.class, B.class})
@EnableAspectJAutoProxy
public class CircularDependency {

    public static void main(String[] args) {
        System.setProperty("test.a", "/sysenv/");
        ApplicationContext context = new AnnotationConfigApplicationContext(CircularDependency.class);
        A a = context.getBean(A.class);
        B b = a.takeB();
        System.out.println(b);
        System.out.println(a.b);
        System.out.println(a.getB());
//        B b = new B();
//        AProxy aProxy = new AProxy(b);
//        A a = ((A) aProxy);
//        System.out.println(a.b);
    }
}

@Component
@Aspect
class Point {
    @Pointcut("execution(* com.cheeseocean.ioc.A.takeB(..))")
    void cut() {
    }

    @Before("cut()")
    void before() {
        System.out.println("before");
    }
}

class Constants {
    @Getter
    private static String C = "/home/";
}

//@Component
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
class A {
    @Autowired
    B b;

    public B takeB() {
        System.out.println("takeB");
        return b;
    }
    public B getB() {
        return b;
    }
}
class AProxy extends A {
    private A targetSource;
    public AProxy(A targetSource) {
        this.targetSource = targetSource;
    }
    @Override
    public B takeB() {
        return targetSource.getB();
    }
}

@Component
class B {
    @Autowired
    A a;

    public A takeA() {
        return a;
    }

    void sayHello() {
        System.out.println("B: sayHello");
    }
}