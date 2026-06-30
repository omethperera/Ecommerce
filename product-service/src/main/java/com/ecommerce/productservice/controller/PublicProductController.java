package com.ecommerce.productservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class PublicProductController {

    private final ProductService productService;

    public PublicProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllActiveProducts() {
        List<ProductResponse> response = productService.getAllActiveProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getActiveProductById(@PathVariable Long productId) {
        ProductResponse response = productService.getActiveProductById(productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchActiveProducts(@RequestParam String keyword) {
        List<ProductResponse> response = productService.searchActiveProducts(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getActiveProductsByCategory(@PathVariable Long categoryId) {
        List<ProductResponse> response = productService.getActiveProductsByCategory(categoryId);
        return ResponseEntity.ok(response);
    }
}