package com.ecommerce.orderservice.dto;

public class CheckoutResponse {

    private String message;
    private Long orderId;

    public CheckoutResponse() {
    }

    public CheckoutResponse(String message, Long orderId) {
        this.message = message;
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}