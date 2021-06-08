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
public class ServiceAutoConfiguration {

    @Bean
    public FlyService flyService(ServiceProperties properties){
        return new FlyService(properties.getName());
    }
}
