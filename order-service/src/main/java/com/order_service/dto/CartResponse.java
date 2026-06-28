package com.order_service.dto;

import java.util.List;

public class CartResponse {

    private String uuid;
    private List<CartItemResponse> items;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }
}
