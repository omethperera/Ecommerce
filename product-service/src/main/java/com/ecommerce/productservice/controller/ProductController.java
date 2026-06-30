package com.ecommerce.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.productservice.dto.ProductCreateRequest;
import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.service.ProductService;
import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request,
            @RequestHeader("X-Seller-Id") Long sellerId) {

        ProductResponse response = productService.createProduct(request, sellerId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/my-products")
    public ResponseEntity<List<ProductResponse>> getMyProducts(
            @RequestHeader("X-Seller-Id") Long sellerId) {

        List<ProductResponse> response = productService.getMyProducts(sellerId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductCreateRequest request,
            @RequestHeader("X-Seller-Id") Long sellerId) {

        ProductResponse response = productService.updateProduct(productId, request, sellerId);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long productId,
            @RequestHeader("X-Seller-Id") Long sellerId) {

        productService.deleteProduct(productId, sellerId);
        return ResponseEntity.ok("Product deleted successfully");
    }
    
}