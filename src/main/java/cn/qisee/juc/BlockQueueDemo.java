package cn.qisee.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.qisee.juc.collection.LinkedBlockingQueue;

public class BlockQueueDemo {
    static LinkedBlockingQueue<String> taskQueue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(BlockQueueDemo::custom);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.execute(BlockQueueDemo::product);
        executor.shutdown();
    }

    public static void product() {
        int cnt = 0;
        try {
            taskQueue.put("Hello Im " + cnt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void custom() {
        int cnt = 0;
        try {
            System.out.println("take: " + taskQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
