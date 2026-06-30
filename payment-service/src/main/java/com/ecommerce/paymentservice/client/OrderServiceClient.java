package com.ecommerce.paymentservice.client;

import com.ecommerce.paymentservice.dto.OrderPaymentDetailsResponse;
import com.ecommerce.paymentservice.dto.PaymentFailedRequest;
import com.ecommerce.paymentservice.dto.PaymentSuccessRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/api/orders/internal/{orderId}")
    OrderPaymentDetailsResponse getOrderDetailsForPayment(@PathVariable("orderId") Long orderId);

    @PostMapping("/api/orders/internal/payment-success")
    Object handlePaymentSuccess(@RequestBody PaymentSuccessRequest request);
    
    @PostMapping("/api/orders/internal/payment-failed")
    Object handlePaymentFailed(@RequestBody PaymentFailedRequest request);
}