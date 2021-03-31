package beer.cheese.ioc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    Lock lock = new ReentrantLock(true);
    AtomicInteger stat = new AtomicInteger(0);
    public static void main(String[] args) {
        Main main = new Main();
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        executor.execute(main::resRace);
//        executor.execute(main::resRace);
//        executor.execute(main::resRace);

//        for(;;){
//            System.out.println("hello");
//        }
//        executor.shutdown();

//        Thread t1 = new Thread(main::resRace);
//        Thread t2 = new Thread(main::resRace);
//        Thread t3 = new Thread(main::resRace);
//        t1.start();
//        t2.start();
//        t3.start();
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(main::print);
        es.execute(main::print1);

    }

    public void print() {
        while(stat.get() < 100){
            System.out.println(stat.incrementAndGet());
            this.notifyAll();
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void print1() {
        while (stat.get() < 100) {
            System.out.println(stat.incrementAndGet());
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resRace(){
        lock.lock();
            for(int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        lock.unlock();
    }
}
