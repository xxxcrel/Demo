package beer.cheese.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceAutoConfiguration implements ApplicationListener, InitializingBean, BeanPostProcessor {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("before event");
        System.setProperty("spring.profiles.include", "service");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
    @Bean
    public FlyService flyService(ServiceProperties properties){
        return new FlyService(properties.getName());
    }
}
