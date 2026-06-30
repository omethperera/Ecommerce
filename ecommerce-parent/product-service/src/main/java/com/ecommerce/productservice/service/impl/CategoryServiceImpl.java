package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.dto.request.CategoryRequest;
import com.ecommerce.productservice.dto.response.CategoryResponse;
import com.ecommerce.productservice.entity.Category;
import com.ecommerce.productservice.exception.BadRequestException;
import com.ecommerce.productservice.exception.DuplicateResourceException;
import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.repository.CategoryRepository;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        String categoryName = request.getName().trim();

        if (categoryRepository.existsByNameIgnoreCase(categoryName)) {
            throw new DuplicateResourceException("Category name already exists");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);

        return mapToCategoryResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : categories) {
            responses.add(mapToCategoryResponse(category));
        }

        return responses;
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        return mapToCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        String newName = request.getName().trim();

        if (!existingCategory.getName().equalsIgnoreCase(newName)
                && categoryRepository.existsByNameIgnoreCase(newName)) {
            throw new DuplicateResourceException("Category name already exists");
        }

        existingCategory.setName(newName);
        existingCategory.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);

        return mapToCategoryResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (productRepository.existsByCategoryId(id)) {
            throw new BadRequestException("Cannot delete category because products exist under it");
        }

        categoryRepository.delete(existingCategory);
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }
}