package com.ecommerce.paymentservice.dto;

import java.math.BigDecimal;

public class PayPalPaymentResponse {

    private Long paymentId;
    private Long orderId;
    private String clientId;
    private String returnUrl;
    private String cancelUrl;
    private BigDecimal amount;
    private String currency;
    private String paypalOrderId;
    private String approveUrl;
    private BigDecimal originalAmountLkr;

    public PayPalPaymentResponse() {
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaypalOrderId() {
        return paypalOrderId;
    }

    public void setPaypalOrderId(String paypalOrderId) {
        this.paypalOrderId = paypalOrderId;
    }

    public String getApproveUrl() {
        return approveUrl;
    }

    public void setApproveUrl(String approveUrl) {
        this.approveUrl = approveUrl;
    }
    
    public BigDecimal getOriginalAmountLkr() {
        return originalAmountLkr;
    }

    public void setOriginalAmountLkr(BigDecimal originalAmountLkr) {
        this.originalAmountLkr = originalAmountLkr;
    }
}