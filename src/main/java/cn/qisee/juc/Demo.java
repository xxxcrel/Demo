package cn.qisee.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Supplier;


public class Demo {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int cnt = 0;
    public Demo(){

    }
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), new ThreadPoolExecutor.DiscardPolicy());
//        executor.execute(Demo::timeoutTask);
//        executor.execute(Demo::timeoutTask);
//        executor.execute(Demo::waitAndRelease);
//        executor.execute(Demo::signal);
        Demo demo = new Demo();
//        ArrayList<String> strings = new ArrayList<>();
//        strings.forEach(System.out::println);
        generator(Demo::new);
        Hello hello = generator(Hello::new);
        supply2((s, i) -> {
            System.out.println(s);
            System.out.println(s);
        });
        executor.execute(Demo::sync);
        executor.execute(Demo::sync2);
        executor.shutdown();
    }
    public static class Hello{
        public Hello(){

        }
    }
    public static <T> T generator(Supplier<T> supplier){
        return supplier.get();
    }

    public static void supply2(BiConsumer<String, Integer> biConsumer){

    }

    public static void hell(String s, Integer i){

    }

    public static  void timeoutTask(){
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

    public static void waitAndRelease() {
        lock.lock();
        while(cnt == 0){
            cnt ++;
            System.out.println("produce something, waiting consumed");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    public static void signal(){
        lock.lock();
        while(cnt == 1){
            cnt = 0;
            System.out.println("consumed, please produce");
            try {
                TimeUnit.SECONDS.sleep(10);
                condition.signal();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    public static synchronized void sync(){
        System.out.println("sync()");
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("release sync()");
    }
    public static void sync2(){
        System.out.println("sync2");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized static void waitAndRelease2(){

    }
}
