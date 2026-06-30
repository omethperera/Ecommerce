package com.ecommerce.paymentservice.dto;

public class PaymentInitiateRequest {

    private Long orderId;
    private String email;

    public PaymentInitiateRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}