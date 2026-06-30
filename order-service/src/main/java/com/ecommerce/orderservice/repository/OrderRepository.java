package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.enums.OrderStatus;
import com.ecommerce.orderservice.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findFirstByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatuses);

    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);

    Optional<Order> findByIdAndUserId(Long id, Long userId);
}