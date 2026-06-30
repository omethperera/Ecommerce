package com.ecommerce.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.productservice.dto.CategoryRequest;
import com.ecommerce.productservice.dto.CategoryResponse;
import com.ecommerce.productservice.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class CategoryController {

    private final ProductService productService;

    public CategoryController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/admin/categories")
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response = productService.createCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/api/admin/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesForAdmin() {
        List<CategoryResponse> response = productService.getAllCategories();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse response = productService.updateCategory(categoryId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        productService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @GetMapping("/api/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesPublic() {
        List<CategoryResponse> response = productService.getAllCategories();
        return ResponseEntity.ok(response);
    }
}