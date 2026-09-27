package io.github.lucasiferreira.produtosapi.controller;

import io.github.lucasiferreira.produtosapi.dto.ProductRequest;
import io.github.lucasiferreira.produtosapi.dto.ProductResponse;
import io.github.lucasiferreira.produtosapi.entity.Product;
import io.github.lucasiferreira.produtosapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Long id) {
        ProductResponse product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest product) {
        ProductResponse p = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> list = productService.findAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable("id") Long id, @RequestBody @Valid ProductRequest request) {
        ProductResponse p = productService.update(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<ProductResponse>> findAllByCategories(@PathVariable("id") Long id) {
        List<ProductResponse> list = productService.findProductsByCategory(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> findByName(@RequestParam String name) {
        List<ProductResponse> list = productService.findByName(name);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductResponse>> findByName(@RequestParam Double price) {
        List<ProductResponse> list = productService.findPriceGreaterThan(price);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductResponse>> findPage(
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "name") String ordem,
            @RequestParam(defaultValue = "asc") String direcao
    ) {
        Page<ProductResponse> page = productService.findPagination(pagina, tamanho, ordem, direcao);
        return ResponseEntity.ok(page);
    }

}
