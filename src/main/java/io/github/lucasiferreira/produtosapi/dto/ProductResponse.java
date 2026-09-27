package io.github.lucasiferreira.produtosapi.dto;

import io.github.lucasiferreira.produtosapi.entity.Product;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Integer quantity,
        Long categoryId
) {
    public ProductResponse(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCategory().getId());
    }
}
