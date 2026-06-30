package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.ApiResponse;
import com.ecommerce.orderservice.dto.OrderPaymentDetailsResponse;
import com.ecommerce.orderservice.dto.PaymentFailedRequest;
import com.ecommerce.orderservice.dto.PaymentSuccessRequest;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/internal")
public class OrderInternalController {

    private final OrderService orderService;

    public OrderInternalController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetailsForPayment(@PathVariable Long orderId) {
        OrderPaymentDetailsResponse response = orderService.getOrderDetailsForPayment(orderId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/payment-success")
    public ResponseEntity<ApiResponse> handlePaymentSuccess(
            @Valid @RequestBody PaymentSuccessRequest request) {

        return ResponseEntity.ok(orderService.handlePaymentSuccess(request));
    }

    @PostMapping("/payment-failed")
    public ResponseEntity<ApiResponse> handlePaymentFailed(
            @Valid @RequestBody PaymentFailedRequest request) {

        return ResponseEntity.ok(orderService.handlePaymentFailed(request));
    }
}