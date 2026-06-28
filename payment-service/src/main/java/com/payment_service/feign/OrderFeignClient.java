package com.payment_service.feign;

import com.payment_service.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "order-service",
        url = "${order-service.url}"
)
public interface OrderFeignClient {

    @GetMapping("/api/v1/orders/{id}")
    OrderResponse getOrderById(@PathVariable Long id);
}