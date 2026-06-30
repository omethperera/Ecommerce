package com.ecommerce.paymentservice.dto;

public class PaymentSuccessRequest {

    private Long orderId;
    private String paymentReference;

    public PaymentSuccessRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}