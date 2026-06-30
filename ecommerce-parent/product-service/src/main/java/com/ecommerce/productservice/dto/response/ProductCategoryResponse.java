package com.ecommerce.productservice.dto.response;

public class ProductCategoryResponse {

    private Long id;
    private String name;

    public ProductCategoryResponse() {
    }

    public ProductCategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}