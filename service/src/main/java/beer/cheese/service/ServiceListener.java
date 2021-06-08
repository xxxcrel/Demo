package beer.cheese.service;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ServiceListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationStartingEvent){
            System.out.println("Staring Event");
            System.setProperty("spring.profiles.include", "service");
        }
    }
}
