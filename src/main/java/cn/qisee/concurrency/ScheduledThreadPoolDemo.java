package cn.qisee.concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduledThreadPoolDemo {
    Set<String> set;
    Lock lock;
    Condition notEmpty;
    ScheduledThreadPoolExecutor executor;
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.schedule(() -> System.out.println("hello"), 3, TimeUnit.SECONDS);
//        ScheduledThreadPoolDemo demo = new ScheduledThreadPoolDemo();
//        demo.start();
//        demo.addUser("wuxc");
//        demo.addUser("hello");
//        demo.addUser("haha");
//        demo.addUser("lala");
//        Thread.sleep(3000L);
//        demo.removeUser("haha");
//        Thread.sleep(2000L);
//        demo.addUser("wuxc");
//
//        Thread.sleep(3000L);
//        demo.removeUser("hello");
//        Thread.sleep(3000L);
//        demo.removeUser("lala");
//        Thread.sleep(3000L);
//        demo.removeUser("wuxc");
    }

    public void start() {
        set = new HashSet<>();
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        executor = new ScheduledThreadPoolExecutor(2);
        executor.scheduleWithFixedDelay(this::periodRun, 1000L, 1000L, TimeUnit.MILLISECONDS);
    }

    public void periodRun() {
        System.out.println("Thread: " + Thread.currentThread());
        System.out.println("----------------------------------");
        lock.lock();
        try {
            if (set.isEmpty()) {
                notEmpty.wait();
            }
            set.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void addUser(String user) {
        lock.lock();
        try {
            set.add(user);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void removeUser(String user) {
        lock.lock();
        try {
            if (set.contains(user)) {
                set.remove(user);
            }
        } finally {
            lock.unlock();
        }
    }
}
