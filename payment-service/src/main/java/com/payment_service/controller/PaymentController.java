package com.payment_service.controller;

import com.payment_service.dto.OrderEvent;
import com.payment_service.kafka.NotificationProducer;
import com.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    private NotificationProducer notificationProducer;

    public PaymentController(
            PaymentService paymentService, NotificationProducer notificationProducer) {

        this.paymentService = paymentService;
        this.notificationProducer = notificationProducer;
    }

    @PostMapping("/create/{orderId}")
    public ResponseEntity<String> createPayment(
            @PathVariable Long orderId)
            throws Exception {

        String paymentUrl =
                paymentService.createPayment(orderId);

        return ResponseEntity.ok(paymentUrl);
    }

    @GetMapping("/success")
    public String success(
            @RequestParam Long orderId) {

        paymentService.markPaymentSuccess(orderId);

        OrderEvent event = new OrderEvent();
        event.setOrderId(orderId);
        event.setEmail("vedm57932@gmail.com");
        event.setMobile("+916268383843");
        event.setStatus("success");
        notificationProducer.sendOrderEvent(event);

        return "Payment Successful for Order ID: " + orderId;
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "Payment Cancelled";
    }

//    @PostMapping("/webhook")
//    public ResponseEntity<String> handleWebhook(
//            @RequestBody String payload) {
//
//        // Read Stripe event
//
//        // If checkout.session.completed
//        // Update payment status = SUCCESS
//
//        return ResponseEntity.ok("received");
//    }

//    @PostMapping("/webhook")
//    public ResponseEntity<String> handleWebhook() {
//
//        return ResponseEntity.ok("received");
//    }
//
}