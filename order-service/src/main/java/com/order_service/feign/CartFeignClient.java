package com.order_service.feign;

import com.order_service.dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cart-service",
        url = "${cart-service.url}"
)
public interface CartFeignClient {

    @GetMapping("/api/v1/cart/{uuid}")
    CartResponse getCart(@PathVariable String uuid);

    @DeleteMapping("/api/v1/cart/{uuid}")
    void clearCart(@PathVariable String uuid);
}
