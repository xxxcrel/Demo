package cn.qisee.concurrency;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuittingCompletable {
    public static void main(String[] args) throws InterruptedException{
        byte b = 0b111;
        System.out.println(b);
//        List<QuittableTask> tasks =
//                IntStream.range(1, 10)
//                        .mapToObj(QuittableTask::new)
//                        .collect(Collectors.toList());
//        List<CompletableFuture<Void>> cfutures =
//                tasks.stream()
//                        .map(CompletableFuture::runAsync)
//                        .collect(Collectors.toList());
//        TimeUnit.SECONDS.sleep(1);
//        tasks.forEach(QuittableTask::quit);
//        cfutures.forEach(CompletableFuture::join);
    }
}
