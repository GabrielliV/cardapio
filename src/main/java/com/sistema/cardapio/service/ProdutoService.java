package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.ProdutoDto;
import com.sistema.cardapio.model.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> produtosPorCategoria(int categoriaId);

    Produto produto(int id);

    int criaProduto(ProdutoDto produto);

    void ativaInativaProduto(int produtoId, boolean status);

    void alteraProduto(int produtoId, ProdutoDto produto);

    void deletaProduto(int produtoId);

}
