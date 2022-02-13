package com.cheeseocean.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {

        executeQueuedTaskAfterShutdown();
    }

    public static void prestartNonCoreThread() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        executor.execute(() -> {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
                System.out.println("finished");
                throw new RuntimeException("Runtime");
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            System.out.println("hello");
        });

        executor.shutdown();

    }

    public static void executeQueuedTaskAfterShutdown() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        executor.execute(() -> {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
                System.out.println("finished");
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            System.out.println("hello");
        });
        executor.shutdown();
    }
}
