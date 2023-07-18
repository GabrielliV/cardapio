package com.sistema.cardapio.controller;

import com.sistema.cardapio.model.Produto;
import com.sistema.cardapio.service.ProdutoService;
import com.sistema.cardapio.service.impl.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cardapio/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("{id_categoria}")
    public List<Produto> produtosPorCategoria(@PathVariable int id_categoria) {
        return produtoService.produtosPorCategoria(id_categoria);
    }
}
