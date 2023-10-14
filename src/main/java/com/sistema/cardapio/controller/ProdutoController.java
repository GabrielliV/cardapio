package com.sistema.cardapio.controller;

import com.sistema.cardapio.dto.ProdutoDto;
import com.sistema.cardapio.model.Produto;
import com.sistema.cardapio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cardapio/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/categoria/{id_categoria}")
    public List<Produto> cardapioProdutosPorCategoria(@PathVariable int id_categoria) {
        return produtoService.produtosPorCategoria(id_categoria);
    }

    @GetMapping("lista/categoria/{id_categoria}")
    public List<Produto> listaProdutosPorCategoria(@PathVariable int id_categoria) {
        return produtoService.todosProdutosPorCategoria(id_categoria);
    }

    @GetMapping("/produto/{id}")
    public Produto produto(@PathVariable int id) {
        return produtoService.produto(id);
    }

    @PostMapping("/status/produto/{produtoId}/{status}")
    public void ativaInativaProduto(@PathVariable int produtoId, @PathVariable boolean status) {
        produtoService.ativaInativaProduto(produtoId, status);
    }

    @PostMapping("/altera/produto/{produtoId}")
    public int alteraProduto(@PathVariable int produtoId, @RequestBody ProdutoDto produto) {
        return produtoService.alteraProduto(produtoId, produto);
    }

    @PostMapping("/deleta/produto/{produtoId}")
    public void deletaProduto(@PathVariable int produtoId) {
        produtoService.deletaProduto(produtoId);
    }

    @PostMapping("/cria/produto")
    public int criaProduto(@RequestBody ProdutoDto produto) {
        return produtoService.criaProduto(produto);
    }

}
