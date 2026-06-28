package com.email_service.kafka;

import com.email_service.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private static final String TOPIC = "orders";

    public void sendOrderEvent(OrderEvent event) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println("Order event sent to topic: " + event);
    }
}
