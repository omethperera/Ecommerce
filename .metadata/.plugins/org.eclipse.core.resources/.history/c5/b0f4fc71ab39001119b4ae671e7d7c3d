package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.dto.StockReduceRequest;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/internal")
public class InternalProductController {

    private final ProductService productService;

    public InternalProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        ProductResponse response = productService.getProductById(productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/stock/reduce")
    public ResponseEntity<String> reduceStock(@RequestBody StockReduceRequest request) {
        productService.reduceStock(request);
        return ResponseEntity.ok("Stock reduced successfully");
    }
}