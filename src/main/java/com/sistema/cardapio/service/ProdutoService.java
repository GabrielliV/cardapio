package com.sistema.cardapio.service;

import com.sistema.cardapio.model.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> produtosPorCategoria(int categoriaId);
}
