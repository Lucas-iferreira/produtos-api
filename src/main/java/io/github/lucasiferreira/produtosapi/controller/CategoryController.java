package io.github.lucasiferreira.produtosapi.controller;

import io.github.lucasiferreira.produtosapi.dto.CategoryRequest;
import io.github.lucasiferreira.produtosapi.dto.CategoryResponse;
import io.github.lucasiferreira.produtosapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.findById(id);
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        CategoryResponse categoryResponse = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }
}
