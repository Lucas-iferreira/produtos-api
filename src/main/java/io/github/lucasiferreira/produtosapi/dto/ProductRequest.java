package io.github.lucasiferreira.produtosapi.dto;

import jakarta.validation.constraints.*;

public record ProductRequest(
        @NotBlank(message = "Não pode conter valor nulo!")
        String name,
        @NotBlank(message = "Não pode conter valor nulo!")
        String description,
        @Positive(message = "Não pode conter valor negativo!")
        @NotNull
        Double price,
        @PositiveOrZero(message = "Numero positivo ou zero!")
        @NotNull
        Integer quantity,
        @Positive
        @NotNull
        Long categoryId) {
}
