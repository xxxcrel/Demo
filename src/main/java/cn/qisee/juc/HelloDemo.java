package cn.qisee.juc;

import java.util.concurrent.TimeUnit;

public class HelloDemo {
    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("你好");
    }
}