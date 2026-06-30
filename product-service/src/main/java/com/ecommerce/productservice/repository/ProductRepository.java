package com.ecommerce.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsActiveTrue();

    List<Product> findByIsActiveTrueAndNameContainingIgnoreCase(String keyword);

    List<Product> findByIsActiveTrueAndCategoryId(Long categoryId);

    List<Product> findBySellerId(Long sellerId);

    Optional<Product> findByIdAndSellerId(Long id, Long sellerId);
}