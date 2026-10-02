package io.github.lucasiferreira.produtosapi.repository;

import io.github.lucasiferreira.produtosapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
