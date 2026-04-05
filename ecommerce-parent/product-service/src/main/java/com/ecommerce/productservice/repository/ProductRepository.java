package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsActiveTrue();

    Optional<Product> findByIdAndIsActiveTrue(Long id);

    List<Product> findByCategoryIdAndIsActiveTrue(Long categoryId);

    boolean existsByCategoryId(Long categoryId);
}