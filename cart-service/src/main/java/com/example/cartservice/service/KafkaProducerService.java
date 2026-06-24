package com.example.cartservice.service;

import com.example.cartservice.dto.CartEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC_NAME = "cart-events";

    private final KafkaTemplate<String, CartEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, CartEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCartEvent(CartEvent cartEvent) {
        kafkaTemplate.send(TOPIC_NAME, cartEvent);
    }
}