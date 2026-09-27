package io.github.lucasiferreira.produtosapi.service;

import io.github.lucasiferreira.produtosapi.dto.CategoryRequest;
import io.github.lucasiferreira.produtosapi.dto.CategoryResponse;
import io.github.lucasiferreira.produtosapi.entity.Category;
import io.github.lucasiferreira.produtosapi.exception.ProductAlreadyExistsException;
import io.github.lucasiferreira.produtosapi.exception.ResourceNotFoundException;
import io.github.lucasiferreira.produtosapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryResponse create(CategoryRequest request) {
        Category category = new Category();
        if (categoryRepository.existsByName(request.name())) {
            throw new ProductAlreadyExistsException("Category already exists!");
        }
        category.setName(request.name());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(savedCategory);
    }

    public CategoryResponse findById(Long id) {
        Category savedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not Found"));
        return new CategoryResponse(savedCategory);
    }
}
