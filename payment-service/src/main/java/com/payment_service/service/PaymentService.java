package com.payment_service.service;

import com.payment_service.dto.OrderResponse;
import com.payment_service.entity.Payment;
import com.payment_service.feign.OrderFeignClient;
import com.payment_service.repository.PaymentRepository;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderFeignClient orderFeignClient;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderFeignClient orderFeignClient) {

        this.paymentRepository = paymentRepository;
        this.orderFeignClient = orderFeignClient;
    }

    public String createPayment(Long orderId) throws Exception {

        OrderResponse order =
                orderFeignClient.getOrderById(orderId);

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(
                                "http://localhost:8085/api/v1/payments/success?orderId=" + orderId)
                        .setCancelUrl(
                                "http://localhost:8085/api/v1/payments/cancel")

                        .addLineItem(
                                SessionCreateParams.LineItem
                                        .builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams
                                                        .LineItem
                                                        .PriceData
                                                        .builder()
                                                        .setCurrency("inr")
                                                        .setUnitAmount(
                                                                order.getTotalAmount()
                                                                        .multiply(
                                                                                BigDecimal.valueOf(100))
                                                                        .longValue()
                                                        )
                                                        .setProductData(
                                                                SessionCreateParams
                                                                        .LineItem
                                                                        .PriceData
                                                                        .ProductData
                                                                        .builder()
                                                                        .setName(
                                                                                "Order #"
                                                                                        + orderId)
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        Session session = Session.create(params);

        Payment payment = new Payment();

        payment.setOrderId(orderId);
        payment.setAmount(order.getTotalAmount());
        payment.setStatus("PENDING");
        payment.setStripeSessionId(session.getId());

        paymentRepository.save(payment);

        return session.getUrl();
    }

    public void markPaymentSuccess(Long orderId) {

        Payment payment =
                paymentRepository.findByOrderId(orderId)
                        .orElseThrow(() ->
                                new RuntimeException("Payment not found"));

        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);
    }
}
