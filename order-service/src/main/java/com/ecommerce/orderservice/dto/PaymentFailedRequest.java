package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.NotNull;

public class PaymentFailedRequest {

    @NotNull(message = "Order id is required")
    private Long orderId;

    public PaymentFailedRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}