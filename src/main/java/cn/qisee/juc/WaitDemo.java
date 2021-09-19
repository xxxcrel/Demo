package cn.qisee.juc;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WaitDemo {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        WaitDemo demo = new WaitDemo();
        demo.sth();
        demo.sth2();
    }

    public void sth() throws InterruptedException {
        lock.lock();
        condition.await(1, TimeUnit.SECONDS);
        lock.unlock();
    }
    public synchronized void sth2() throws InterruptedException {
        wait(1);
    }

}
