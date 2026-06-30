package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.AddToCartRequest;
import com.ecommerce.orderservice.dto.CheckoutResponse;
import com.ecommerce.orderservice.dto.ApiResponse;
import com.ecommerce.orderservice.dto.CartResponse;
import com.ecommerce.orderservice.dto.CheckoutRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.dto.UpdateCartItemRequest;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderUserController {

    private final OrderService orderService;

    public OrderUserController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/cart/items")
    public ResponseEntity<ApiResponse> addToCart(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody AddToCartRequest request) {

        return ResponseEntity.ok(orderService.addToCart(userId, request));
    }

    @GetMapping("/cart")
    public ResponseEntity<CartResponse> getCart(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(orderService.getCart(userId));
    }

    @PutMapping("/cart/items/{itemId}")
    public ResponseEntity<ApiResponse> updateCartItem(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long itemId,
            @Valid @RequestBody UpdateCartItemRequest request) {

        return ResponseEntity.ok(orderService.updateCartItem(userId, itemId, request));
    }

    @DeleteMapping("/cart/items/{itemId}")
    public ResponseEntity<ApiResponse> removeCartItem(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long itemId) {

        return ResponseEntity.ok(orderService.removeCartItem(userId, itemId));
    }

    @DeleteMapping("/cart")
    public ResponseEntity<ApiResponse> clearCart(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(orderService.clearCart(userId));
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CheckoutRequest request) {

        return ResponseEntity.ok(orderService.checkout(userId, request));
    }

    @GetMapping("/my/current")
    public ResponseEntity<List<OrderResponse>> getMyCurrentOrders(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(orderService.getMyCurrentOrders(userId));
    }

    @GetMapping("/my/history")
    public ResponseEntity<List<OrderResponse>> getMyOrderHistory(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(orderService.getMyOrderHistory(userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderByIdForUser(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.getOrderByIdForUser(userId, orderId));
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<ApiResponse> cancelOrder(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.cancelOrder(userId, orderId));
    }
}