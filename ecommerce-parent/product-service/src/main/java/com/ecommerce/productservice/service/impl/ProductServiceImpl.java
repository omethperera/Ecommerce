package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.dto.request.ProductImageRequest;
import com.ecommerce.productservice.dto.request.ProductRequest;
import com.ecommerce.productservice.dto.response.ProductCategoryResponse;
import com.ecommerce.productservice.dto.response.ProductImageResponse;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.entity.Category;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.entity.ProductImage;
import com.ecommerce.productservice.exception.BadRequestException;
import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.repository.CategoryRepository;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));

        validatePrimaryImageRule(request.getImages());

        Product product = new Product();
        product.setName(request.getName().trim());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setIsActive(request.getIsActive());
        product.setCategory(category);
        product.setImages(new ArrayList<>());

        setProductImages(product, request.getImages());

        Product savedProduct = productRepository.save(product);

        return mapToProductResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));

        validatePrimaryImageRule(request.getImages());

        existingProduct.setName(request.getName().trim());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStockQuantity(request.getStockQuantity());
        existingProduct.setIsActive(request.getIsActive());
        existingProduct.setCategory(category);

        existingProduct.getImages().clear();
        setProductImages(existingProduct, request.getImages());

        Product updatedProduct = productRepository.save(existingProduct);

        return mapToProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existingProduct.setIsActive(false);
        productRepository.save(existingProduct);
    }

    @Override
    public List<ProductResponse> getAllActiveProducts() {
        List<Product> products = productRepository.findByIsActiveTrue();
        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            responses.add(mapToProductResponse(product));
        }

        return responses;
    }

    @Override
    public ProductResponse getActiveProductById(Long id) {
        Product product = productRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Active product not found with id: " + id));

        return mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getActiveProductsByCategory(Long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        List<Product> products = productRepository.findByCategoryIdAndIsActiveTrue(categoryId);
        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {
            responses.add(mapToProductResponse(product));
        }

        return responses;
    }

    private void validatePrimaryImageRule(List<ProductImageRequest> images) {
        if (images == null || images.isEmpty()) {
            return;
        }

        int primaryCount = 0;

        for (ProductImageRequest image : images) {
            if (Boolean.TRUE.equals(image.getIsPrimary())) {
                primaryCount++;
            }
        }

        if (primaryCount > 1) {
            throw new BadRequestException("A product can have at most one primary image");
        }
    }

    private void setProductImages(Product product, List<ProductImageRequest> imageRequests) {
        if (imageRequests == null || imageRequests.isEmpty()) {
            return;
        }

        for (ProductImageRequest imageRequest : imageRequests) {
            ProductImage image = new ProductImage();
            image.setImageUrl(imageRequest.getImageUrl());
            image.setIsPrimary(Boolean.TRUE.equals(imageRequest.getIsPrimary()));
            image.setProduct(product);

            product.getImages().add(image);
        }
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setIsActive(product.getIsActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        ProductCategoryResponse categoryResponse = new ProductCategoryResponse();
        categoryResponse.setId(product.getCategory().getId());
        categoryResponse.setName(product.getCategory().getName());
        response.setCategory(categoryResponse);

        List<ProductImageResponse> imageResponses = new ArrayList<>();
        for (ProductImage image : product.getImages()) {
            ProductImageResponse imageResponse = new ProductImageResponse();
            imageResponse.setId(image.getId());
            imageResponse.setImageUrl(image.getImageUrl());
            imageResponse.setIsPrimary(image.getIsPrimary());
            imageResponses.add(imageResponse);
        }

        response.setImages(imageResponses);

        return response;
    }
}