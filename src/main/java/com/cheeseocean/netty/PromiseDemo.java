package com.cheeseocean.netty;

import java.util.concurrent.TimeUnit;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;

public class PromiseDemo {
    public static void main(String[] args) {
        PromiseDemo demo = new PromiseDemo();
        Promise<String> promise = demo.doSomething();
        promise.addListener((f) -> {
            System.out.println(f.get() + " do something is done");
        });

    }

    Promise<String> doSomething() {
        DefaultEventLoopGroup eventLoop = new DefaultEventLoopGroup();
        DefaultPromise<String> promise = new DefaultPromise<String>(eventLoop.next());
        eventLoop.schedule(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    promise.setSuccess("execute success");
                } catch (InterruptedException e) {
                    promise.setFailure(e);
                }
                return promise;
        }, 0L, TimeUnit.SECONDS);
        return promise;
    }
}
