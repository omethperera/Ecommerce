package com.ecommerce.paymentservice.dto;

public class PayPalCaptureRequest {

    private Long orderId;
    private String paypalOrderId;

    public PayPalCaptureRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaypalOrderId() {
        return paypalOrderId;
    }

    public void setPaypalOrderId(String paypalOrderId) {
        this.paypalOrderId = paypalOrderId;
    }
}