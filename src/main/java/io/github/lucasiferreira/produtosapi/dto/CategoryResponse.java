package io.github.lucasiferreira.produtosapi.dto;

import io.github.lucasiferreira.produtosapi.entity.Category;

public record CategoryResponse(
        Long id,
        String name
) {
    public CategoryResponse(Category category) {
        this(category.getId(), category.getName());
    }
}
