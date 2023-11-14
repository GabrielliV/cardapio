package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.ProdutoDto;
import com.sistema.cardapio.model.Produto;

import java.io.IOException;
import java.util.List;

public interface ProdutoService {
    List<Produto> produtosPorCategoria(int categoriaId);

    List<Produto> todosProdutosPorCategoria(int categoriaId);

    List<Produto> buscarProdutos(String nome);

    Produto produto(int id);

    int criaProduto(ProdutoDto produto);

    void ativaInativaProduto(int produtoId, boolean status);

    int alteraProduto(int produtoId, ProdutoDto produto);

    void deletaProduto(int produtoId);

}
