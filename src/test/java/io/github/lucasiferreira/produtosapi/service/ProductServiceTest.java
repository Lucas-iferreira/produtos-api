package io.github.lucasiferreira.produtosapi.service;

import io.github.lucasiferreira.produtosapi.dto.ProductResponse;
import io.github.lucasiferreira.produtosapi.entity.Category;
import io.github.lucasiferreira.produtosapi.entity.Product;
import io.github.lucasiferreira.produtosapi.exception.ResourceNotFoundException;
import io.github.lucasiferreira.produtosapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void deveEncontrarProdutoPorId() {
        Category category = new Category();
        category.setId(1L);
        Product product = new Product();
        product.setId(1L);
        product.setCategory(category);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponse resultado = productService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());

    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExiste(){
        Long id = 999L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.findById(id)
                );
    }
}
