package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findProdutosByCategoriaIdAndAtivo(int id_categoria, boolean ativo);

    Produto getProdutoById(int id);
}