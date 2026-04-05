package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllActiveProducts() {
        return productService.getAllActiveProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getActiveProductById(@PathVariable Long id) {
        return productService.getActiveProductById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> getActiveProductsByCategory(@PathVariable Long categoryId) {
        return productService.getActiveProductsByCategory(categoryId);
    }
}