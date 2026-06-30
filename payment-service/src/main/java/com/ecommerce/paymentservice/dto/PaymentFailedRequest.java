package com.ecommerce.paymentservice.dto;

public class PaymentFailedRequest {

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