package cn.qisee.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
