package cn.qisee.aop;

import org.springframework.stereotype.Component;

@Component
public class StaticMethod {

    public String staticMethod(){
        System.out.println("static method");
        return "hello world";
    }
}
