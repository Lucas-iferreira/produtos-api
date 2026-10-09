package io.github.lucasiferreira.produtosapi.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Campo não pode ficar em branco!")
        String username,
        @NotBlank(message = "Campo não pode ficar em branco!")
        String password
) {
}
