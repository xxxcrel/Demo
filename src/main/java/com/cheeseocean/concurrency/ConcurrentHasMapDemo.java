package com.cheeseocean.concurrency;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ConcurrentHasMapDemo {
    private final Log logger = LogFactory.getLog(getClass());
    private ConcurrentHashMap<String, Runnable> map = new ConcurrentHashMap<>();
    private ThreadPoolExecutor executor;
    private static final int TASKS  = 100;
    private AtomicInteger executingTasks = new AtomicInteger(0);
    private AtomicInteger completedTasks = new AtomicInteger(0);
    private AtomicInteger discardTasks = new AtomicInteger(0);
    private AtomicInteger rejectedTasks = new AtomicInteger(0);
    public static void main(String[] args) {
        ConcurrentHasMapDemo demo = new ConcurrentHasMapDemo();
        demo.init();
        demo.patchTasks();
        demo.countTasks();
        demo.shutdown();
    }

    private void init(){
        executor = new ThreadPoolExecutor(
                10,
                10,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("Task-").build(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        logger.info(Thread.currentThread().getName());
                        logger.info("reject");
                        rejectedTasks.incrementAndGet();
                    }
                });

    }


    public void patchTasks(){
        List<WorkRunnable> tasks = IntStream
                .rangeClosed(1, TASKS)
                .mapToObj(i -> new WorkRunnable("id" + i))
                .collect(Collectors.toList());
        tasks.stream().parallel().forEach(task -> {
            if(canExecute()){
                executor.execute(task);
                executingTasks.incrementAndGet();
//                map.put(task.getId(), task);
            }else{
                discardTasks.incrementAndGet();
            }
        });
    }

    public void countTasks(){
        logger.info("All Tasks: " + TASKS);
        logger.info("Completed Tasks: " + completedTasks.get());
        logger.info("Discard Tasks: " + discardTasks.get());
        logger.info("Rejected Tasks: " + rejectedTasks.get());
    }

    public void shutdown(){
        executor.shutdown();
    }
    public boolean canExecute(){
//        if(map.size() >= 10){
//            return false;
//        }
//        if(logger.isDebugEnabled()){
//            logger.debug("executor is free");
//        }
//        return true;
        return executingTasks.get() < 10;
    }

    private class WorkRunnable implements Runnable{
        public String id;
        public WorkRunnable(String id){
            this.id = id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public void run() {
            System.out.println("hello i'm " + id);
            completedTasks.incrementAndGet();
            executingTasks.decrementAndGet();
//            map.remove(id);
        }
    }
}
