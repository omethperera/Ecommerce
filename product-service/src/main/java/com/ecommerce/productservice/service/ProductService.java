package com.ecommerce.productservice.service;

import org.springframework.stereotype.Service;
import com.ecommerce.productservice.dto.StockReduceRequest;
import com.ecommerce.productservice.dto.StockReduceItemRequest;

import com.ecommerce.productservice.dto.ProductCreateRequest;
import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.entity.Category;
import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.CategoryRepository;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.dto.CategoryRequest;
import com.ecommerce.productservice.dto.CategoryResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse createProduct(ProductCreateRequest request, Long sellerId) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setStockQuantity(request.getStockQuantity());
        product.setSellerId(sellerId);
        product.setCategory(category);

        if (request.getIsActive() != null) {
            product.setIsActive(request.getIsActive());
        } else {
            product.setIsActive(true);
        }

        Product savedProduct = productRepository.save(product);

        return mapToProductResponse(savedProduct);
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImageUrl(product.getImageUrl());
        response.setStockQuantity(product.getStockQuantity());
        response.setIsActive(product.getIsActive());
        response.setSellerId(product.getSellerId());

        if (product.getCategory() != null) {
            response.setCategoryId(product.getCategory().getId());
            response.setCategoryName(product.getCategory().getName());
        }

        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        return response;
    }
    
    public List<ProductResponse> getMyProducts(Long sellerId) {
        List<Product> products = productRepository.findBySellerId(sellerId);
        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(mapToProductResponse(product));
        }

        return responseList;
    }
    
    public ProductResponse updateProduct(Long productId, ProductCreateRequest request, Long sellerId) {
        Product product = productRepository.findByIdAndSellerId(productId, sellerId)
                .orElseThrow(() -> new RuntimeException("Product not found or does not belong to this seller"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);

        if (request.getIsActive() != null) {
            product.setIsActive(request.getIsActive());
        }

        Product updatedProduct = productRepository.save(product);
        return mapToProductResponse(updatedProduct);
    }
    
    public void deleteProduct(Long productId, Long sellerId) {
        Product product = productRepository.findByIdAndSellerId(productId, sellerId)
                .orElseThrow(() -> new RuntimeException("Product not found or does not belong to this seller"));

        productRepository.delete(product);
    }
    
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);
        return mapToCategoryResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            responseList.add(mapToCategoryResponse(category));
        }

        return responseList;
    }

    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return mapToCategoryResponse(updatedCategory);
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        categoryRepository.delete(category);
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }
    
    public List<ProductResponse> getAllActiveProducts() {
        List<Product> products = productRepository.findByIsActiveTrue();
        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(mapToProductResponse(product));
        }

        return responseList;
    }

    public ProductResponse getActiveProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        if (!Boolean.TRUE.equals(product.getIsActive())) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        return mapToProductResponse(product);
    }

    public List<ProductResponse> searchActiveProducts(String keyword) {
        List<Product> products = productRepository.findByIsActiveTrueAndNameContainingIgnoreCase(keyword);
        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(mapToProductResponse(product));
        }

        return responseList;
    }

    public List<ProductResponse> getActiveProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByIsActiveTrueAndCategoryId(categoryId);
        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(mapToProductResponse(product));
        }

        return responseList;
    }
    
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        return mapToProductResponse(product);
    }

    public void reduceStock(StockReduceRequest request) {
        for (StockReduceItemRequest item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + item.getProductId()));

            if (!Boolean.TRUE.equals(product.getIsActive())) {
                throw new RuntimeException("Product is inactive with id: " + item.getProductId());
            }

            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product id: " + item.getProductId());
            }

            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            productRepository.save(product);
        }
    }
}