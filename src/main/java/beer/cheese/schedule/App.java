package beer.cheese.schedule;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@EnableScheduling
@ComponentScan
@EnableAsync
@Configuration
public class App {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class, TimingPool.class);
//        TimingPool pool = context.getBean(TimingPool.class);
//        pool.timeSchedule();
//        System.out.println("async finish");
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        System.out.println(System.currentTimeMillis());
        long delay = System.currentTimeMillis() + 3000;
        executor.schedule(() -> {
            executor.schedule(() -> {
                System.out.println("innner run ");
                System.out.println(System.currentTimeMillis());
            }, delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }, 3000, TimeUnit.MILLISECONDS);
//        Class clazz = TimingPool.class;

//        clazz.getMethod("task", String.class).invoke(clazz.newInstance(), "hell");
    }

    @Bean("myTimingPool")
    public Executor getTimingPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 4);
        // use SynchronousQueue
        executor.setQueueCapacity(0);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("omsTimingPool-");
//        executor.setRejectedExecutionHandler();
        return executor;
    }

    @Bean("myTimingScheduler")
    public Executor getTimingScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
        scheduler.setThreadNamePrefix("MyTimingScheduler");
        scheduler.setDaemon(false);
        return scheduler;
    }
}

@Component
class TimingPool {
    @Scheduled(fixedDelay = 15000L)
//    @Async("myTimingPool")
    public void timeSchedule() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
    }

    public void task(String s) {
        System.out.println(s);
    }
}