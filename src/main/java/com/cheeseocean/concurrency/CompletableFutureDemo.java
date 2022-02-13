package com.cheeseocean.concurrency;//package cn.qisee.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100));
        List<Future<List<String>>> list = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Future<List<String>> future = CompletableFuture.supplyAsync(() -> Arrays.asList("hello"), executor);
            list.add(future);
        });
        list.forEach(future -> {
            try {
                finalList.addAll(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        list.forEach(System.out::println);
        System.out.println("finish");
    }
}
