package io.github.lucasiferreira.produtosapi.service;

import io.github.lucasiferreira.produtosapi.dto.ProductRequest;
import io.github.lucasiferreira.produtosapi.dto.ProductResponse;
import io.github.lucasiferreira.produtosapi.entity.Category;
import io.github.lucasiferreira.produtosapi.entity.Product;
import io.github.lucasiferreira.produtosapi.exception.ProductAlreadyExistsException;
import io.github.lucasiferreira.produtosapi.exception.ResourceNotFoundException;
import io.github.lucasiferreira.produtosapi.repository.CategoryRepository;
import io.github.lucasiferreira.produtosapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional()
    public ProductResponse create(ProductRequest request) {
        Product product = new Product();
        if (productRepository.existsByName(request.name())) {
            throw new ProductAlreadyExistsException("Product already exists!");
        }
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not Found!"));
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        return new ProductResponse(savedProduct);

    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found"));
        return new ProductResponse(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        List<Product> produtos = productRepository.findAll();
        return produtos.stream().map(ProductResponse::new).toList();

    }


    @Transactional()
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    @Transactional()
    public ProductResponse update(Long id, ProductRequest request) {
        Product p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found"));
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not Found!"));

        p.setName(request.name());
        p.setDescription(request.description());
        p.setPrice(request.price());
        p.setQuantity(request.quantity());
        p.setCategory(category);

        Product productSave = p; //Por conta do recurs do dirty checking do hibernate, ativado pelo commit do Transactional, 'flush', o hibernate descobre
        //quais campos mudaram e gera os comandos UPDATE automaticamente
        return new ProductResponse(productSave);

    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findProductsByCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not Found");
        }
        List<ProductResponse> responseList = productRepository.findByCategoryId(id).stream().map(ProductResponse::new).toList();
        return new ArrayList<>(responseList);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findByName(String name) {
        List<ProductResponse> responseList = productRepository.findByName(name).stream().map(ProductResponse::new).toList();
        return new ArrayList<>(responseList);
    }


    @Transactional(readOnly = true)
    public List<ProductResponse> findPriceGreaterThan(Double price) {
        List<ProductResponse> responseList = productRepository.findByPriceGreaterThan(price).stream()
                .map(ProductResponse::new).toList();
        return new ArrayList<>(responseList);
    }


    @Transactional(readOnly = true)
    public Page<ProductResponse> findPagination(int pagina, int tamanho, String ordem, String direcao) {

        int tamanhoFinal = Math.min(Math.max(tamanho, 1), 10);
        int paginaFinal = Math.max(pagina, 0);

        Sort.Direction direction = "desc".equalsIgnoreCase(direcao) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(paginaFinal, tamanhoFinal, Sort.by(direction, ordem));

        Page<ProductResponse> page = productRepository.findAll(pageable).map(ProductResponse::new);

        return page;
    }
}
