package com.order_service.service;

import com.order_service.dto.CartItemResponse;
import com.order_service.dto.CartResponse;
import com.order_service.entity.Order;
import com.order_service.entity.OrderItem;
import com.order_service.feign.CartFeignClient;
import com.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartFeignClient cartFeignClient;

    public OrderService(OrderRepository orderRepository,
                        CartFeignClient cartFeignClient) {
        this.orderRepository = orderRepository;
        this.cartFeignClient = cartFeignClient;
    }

    public Order createOrder(String cartUuid) {

        CartResponse cart = cartFeignClient.getCart(cartUuid);

        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setCartUuid(cartUuid);
        order.setStatus("PLACED");

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItemResponse cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(cartItem.getProductId());
            orderItem.setBrandId(cartItem.getBrandId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());

            orderItem.setOrder(order);

            order.getItems().add(orderItem);

            totalAmount = totalAmount.add(
                    cartItem.getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );
        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // Clear cart after successful order creation
        cartFeignClient.clearCart(cartUuid);

        return savedOrder;
    }
    public Order getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found"));
    }
}