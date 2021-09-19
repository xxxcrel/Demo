package cn.qisee.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuittableTask implements Runnable {
    final int id;
    private AtomicBoolean running =
            new AtomicBoolean(true);

    public QuittableTask(int id) {
        this.id = id;
    }

    public void quit() {
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(id + " ");  // [2]
    }
}
