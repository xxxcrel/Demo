package beer.cheese.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            return "hello";
        }).handle((r, e) ->{
            return 1;
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
        System.out.println("end");
    }
}
