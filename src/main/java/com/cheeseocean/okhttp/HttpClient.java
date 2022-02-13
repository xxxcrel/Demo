package com.cheeseocean.okhttp;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class HttpClient {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(100, 200, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("reject: " + ((HttpHandler)r).identity);
            }
        });
        List<Runnable> tasks = IntStream
                .range(1, 20000)
                .mapToObj(HttpHandler::new)
                .collect(Collectors.toList());
        tasks.stream().parallel().forEach(executor::execute);
        executor.shutdown();
    }

    private static class HttpHandler implements Runnable{
        int identity;
        static AtomicInteger errorCount = new AtomicInteger(0);
        public HttpHandler(int i){
            this.identity = i;
        }

        @Override
        public void run() {
            try {
                String result = HttpUtils.get("localhost:10000", "/hello");
//                System.out.println(result);
            }catch (Exception e){
                errorCount.incrementAndGet();
                System.out.println(e.getMessage());
                log.error(e.getClass().getSimpleName());
                log.error(e);
            }
        }
    }
}
