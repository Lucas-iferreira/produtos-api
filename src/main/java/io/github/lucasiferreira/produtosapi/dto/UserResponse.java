package io.github.lucasiferreira.produtosapi.dto;

import io.github.lucasiferreira.produtosapi.entity.User;
import io.github.lucasiferreira.produtosapi.enums.Role;

public record UserResponse(
        Long id,
        String username,
        Role role
) {
    public UserResponse(User user) {
        this(user.getId(),user.getUsername(), user.getRole());
    }
}
