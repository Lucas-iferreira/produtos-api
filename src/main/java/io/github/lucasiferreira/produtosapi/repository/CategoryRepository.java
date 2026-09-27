package io.github.lucasiferreira.produtosapi.repository;

import io.github.lucasiferreira.produtosapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String category);
}
