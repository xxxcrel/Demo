package com.cheeseocean.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = {"kafka_test"}, groupId = "1")
    public void receive(ConsumerRecord<String, String> data) {
        System.out.println(data);
    }
}
