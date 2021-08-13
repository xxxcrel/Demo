package cn.qisee.aop;

import org.springframework.stereotype.Component;

@Component
public class Box {

    public Box(){

    }
    private String something;

    public void setSomething(String sth){
        System.out.println("set something");
        this.something = sth;
    }
}
