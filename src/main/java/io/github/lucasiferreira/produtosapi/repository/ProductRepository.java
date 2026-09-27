package io.github.lucasiferreira.produtosapi.repository;

import io.github.lucasiferreira.produtosapi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String product);

    List<Product> findByCategoryId(Long id);

    List<Product> findByName(String name);

    List<Product> findByPriceGreaterThan(Double price);


    //    @Query("""
//            SELECT p FROM Product p JOIN FETCH p.category
//            """)
    @EntityGraph(attributePaths = "category")
    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);
}
