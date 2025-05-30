package io.github.lucasiferreira.produtosapi.controller;


import io.github.lucasiferreira.produtosapi.model.Produto;
import io.github.lucasiferreira.produtosapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produto recebido: " + produto);
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        repository.save(produto);
        return produto;
    }

    @GetMapping("{id}")
    public Produto obterPorId(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable String id) {
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizando(@PathVariable String id, @RequestBody Produto produto) {
        produto.setId(id);
        repository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome) {
        return repository.findByNome(nome);
    }
}

