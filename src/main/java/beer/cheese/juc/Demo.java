package beer.cheese.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Demo {

    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), new ThreadPoolExecutor.DiscardPolicy());
        executor.execute(Demo::timeoutTask);
        executor.execute(Demo::timeoutTask);
        executor.shutdown();
    }

    public static void timeoutTask(){
        lock.lock();
        System.out.println("after lock");
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("hello");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
