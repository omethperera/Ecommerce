package com.ecommerce.productservice.dto.response;

public class ProductImageResponse {

    private Long id;
    private String imageUrl;
    private Boolean isPrimary;

    public ProductImageResponse() {
    }

    public ProductImageResponse(Long id, String imageUrl, Boolean isPrimary) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.isPrimary = isPrimary;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
}