package cn.qisee.aop;

import org.springframework.stereotype.Component;

@Component
public class StaticMethod implements IStaticMethod{

    public String staticMethod(){
        System.out.println("static method");
        return "hello world";
    }

    @Override
    public String doSth() {
        System.out.println("doSth");
        return "doSth";
    }
}