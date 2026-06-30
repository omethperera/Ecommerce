package com.ecommerce.productservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false, length = 255)
    private String imageUrl;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductImage() {
    }

    public ProductImage(Long id, String imageUrl, Boolean isPrimary, Product product) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.isPrimary = isPrimary;
        this.product = product;
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

    public Product getProduct() {
        return product;
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

    public void setProduct(Product product) {
        this.product = product;
    }
}