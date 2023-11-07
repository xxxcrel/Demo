package com.cheeseocean.concurrency;

import java.util.concurrent.CountDownLatch;

public class MaxThread {
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            try{
                i++;
                new Thread(new NotStop()).start();
            }catch(Exception e){
                System.out.println("i:" + i);
            }
        }
    }
    static class NotStop implements Runnable{
        public CountDownLatch latch;
        public NotStop(){
            latch = new CountDownLatch(1);
        }
        public void run(){
            try{
                latch.await();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
