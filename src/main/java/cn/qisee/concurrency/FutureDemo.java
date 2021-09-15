package cn.qisee.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(2, 4, 10,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());
        List<Future<List<String>>> list = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Future<List<String>> future = CompletableFuture.supplyAsync(() -> Arrays.asList("hello"), executor);
            list.add(future);
        });
        List<String> finalList = new ArrayList<>();
        list.forEach(future -> {
            try {
                List<String> tmp = future.get();
                finalList.addAll(tmp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            future.cancel(true);
        });
        finalList.forEach(System.out::println);
        System.out.println("finish");
    }

    public void sep(){
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
    }
}
