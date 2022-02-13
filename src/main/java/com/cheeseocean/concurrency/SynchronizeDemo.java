package com.cheeseocean.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizeDemo {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        SynchronizeDemo sy = new SynchronizeDemo();
        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    new Thread(sy::doSth).start();
                });
    }

    public synchronized void doSth() {
        System.out.println("doSth");
        next();
        System.out.println("after next()");
    }

    public void next() {
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
