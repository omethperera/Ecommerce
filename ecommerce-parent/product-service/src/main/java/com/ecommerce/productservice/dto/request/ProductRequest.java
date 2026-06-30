package com.ecommerce.productservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 150, message = "Product name must not exceed 150 characters")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Product active status is required")
    private Boolean isActive;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @Valid
    private List<ProductImageRequest> images;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, BigDecimal price, Integer stockQuantity,
                          Boolean isActive, Long categoryId, List<ProductImageRequest> images) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.isActive = isActive;
        this.categoryId = categoryId;
        this.images = images;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public List<ProductImageRequest> getImages() {
        return images;
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

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setImages(List<ProductImageRequest> images) {
        this.images = images;
    }
}