package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.dto.ProdutoDto;
import com.sistema.cardapio.model.Categoria;
import com.sistema.cardapio.model.Produto;
import com.sistema.cardapio.repository.CategoriaRepository;
import com.sistema.cardapio.repository.ProdutoRepository;
import com.sistema.cardapio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository){
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Produto> produtosPorCategoria(int id_categoria) {
        return produtoRepository.findProdutosByCategoriaIdAndAtivo(id_categoria, true);
    }

    @Override
    public List<Produto> todosProdutosPorCategoria(int id_categoria) {
        return produtoRepository.findProdutosByCategoriaIdOrderByAtivoDesc(id_categoria);
    }

    @Override
    public Produto produto(int id) {
        return produtoRepository.getProdutoById(id);
    }

    @Override
    public int criaProduto(ProdutoDto produto) {
        Produto produtoResponse = new Produto();

        produtoResponse.setNome(produto.getNome());
        produtoResponse.setDescricao(produto.getDescricao());
        produtoResponse.setPreco(produto.getPreco());
        produtoResponse.setAtivo(true);
        produtoResponse.setFoto(produto.getFoto());

        Categoria categoria = categoriaRepository.getCategoriaById(produto.getCategoriaId());

        produtoResponse.setCategoria(categoria);

        produtoRepository.save(produtoResponse);

        return produtoResponse.getId();
    }

    @Override
    public void ativaInativaProduto(int produtoId, boolean status) {
        Produto produto = produtoRepository.getProdutoById(produtoId);

        produto.setAtivo(!status);

        produtoRepository.save(produto);
    }

    @Override
    public int alteraProduto(int produtoId, ProdutoDto produto) {
        Produto produtoResponse = produtoRepository.getProdutoById(produtoId);

        produtoResponse.setNome(produto.getNome());
        produtoResponse.setDescricao(produto.getDescricao());
        produtoResponse.setPreco(produto.getPreco());
        produtoResponse.setFoto(produto.getFoto());

        Categoria categoria = categoriaRepository.getCategoriaById(produto.getCategoriaId());

        produtoResponse.setCategoria(categoria);

        produtoRepository.save(produtoResponse);

        return produtoResponse.getId();
    }

    @Override
    public void deletaProduto(int produtoId) {
        Produto produto = produtoRepository.getProdutoById(produtoId);

        produtoRepository.delete(produto);
    }
}
