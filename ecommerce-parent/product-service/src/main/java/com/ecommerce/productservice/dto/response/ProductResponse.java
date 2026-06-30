package com.ecommerce.productservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductCategoryResponse category;
    private List<ProductImageResponse> images;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, String description, BigDecimal price,
                           Integer stockQuantity, Boolean isActive, LocalDateTime createdAt,
                           LocalDateTime updatedAt, ProductCategoryResponse category,
                           List<ProductImageResponse> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ProductCategoryResponse getCategory() {
        return category;
    }

    public List<ProductImageResponse> getImages() {
        return images;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCategory(ProductCategoryResponse category) {
        this.category = category;
    }

    public void setImages(List<ProductImageResponse> images) {
        this.images = images;
    }
}