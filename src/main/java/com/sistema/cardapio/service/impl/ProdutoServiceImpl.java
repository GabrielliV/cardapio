package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.model.Produto;
import com.sistema.cardapio.repository.ProdutoRepository;
import com.sistema.cardapio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> produtosPorCategoria(int id_categoria) {
        return produtoRepository.findProdutosByCategoriaIdAndAtivo(id_categoria, true);
    }

    @Override
    public Produto produto(int id) {
        return produtoRepository.getProdutoById(id);
    }
}
