package io.github.lucasiferreira.produtosapi.repository;

import io.github.lucasiferreira.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
