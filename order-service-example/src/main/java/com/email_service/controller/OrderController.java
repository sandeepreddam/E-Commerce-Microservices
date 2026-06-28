package com.email_service.controller;

import com.email_service.dto.OrderEvent;
import com.email_service.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    @PostMapping
    public String placeOrder(@RequestBody OrderEvent event) {

        producer.sendOrderEvent(event);

        return "Order Event Sent Successfully";
    }
}
