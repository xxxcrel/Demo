package cn.qisee.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.util.ThreadInterruptedException;

public class ThreadPoolDemo {
    public static void main(String[] args) {
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
}
