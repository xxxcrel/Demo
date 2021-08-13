package cn.qisee.ioc;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuittingCompletable {
    public static void main(String[] args) {
        List<QuittableTask> tasks =
                IntStream.range(1, 150)
                        .mapToObj(QuittableTask::new)
                        .collect(Collectors.toList());
        List<CompletableFuture> cfs =
                tasks.stream()
                        .map(CompletableFuture::runAsync)
                        .collect(Collectors.toList());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tasks.forEach(QuittableTask::quit);

        cfs.forEach(CompletableFuture::join);
    }
}

class QuittableTask implements Runnable {
    private int id;
    private AtomicBoolean running = new AtomicBoolean(true);

    public QuittableTask(int id) {
        this.id = id;
    }

    public void quit() {
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(id + " ");
    }
}
