package com.cheeseocean.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@Controller
@CommonsLog
@RestController
@CrossOrigin
public class WebsocktApp {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(WebsocktApp.class, WebSocketConfig.class)
                .run(args);
    }

    @RequestMapping("/download")
    public void download(){
        System.out.println("download");
    }
//    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }
    @SubscribeMapping("/haha")
    public void subscribeHaha(){
        log.info("haha");
    }
    @MessageMapping("/greeting")
    @SendTo("/broadcast/greeting")
    public String greeting(String message) {
        log.info("received message " + message);
        return "hello " + message;
    }
    @MessageMapping("/notify")
    public void hello(String message) {
        log.info("hello start send");
        messagingTemplate.convertAndSend("/topic/notification", "notify something");
    }

    @Configuration
    @EnableWebSocketMessageBroker
    static class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
            registry.enableSimpleBroker("/topic", "/broadcast");
            registry.setApplicationDestinationPrefixes("/cheese");
        }

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/sockjs").setAllowedOriginPatterns("*").withSockJS();
        }
    }
}
