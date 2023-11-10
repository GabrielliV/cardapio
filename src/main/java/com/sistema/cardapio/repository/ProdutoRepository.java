package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM Produto p " +
            "WHERE p.categoria.id = :categoriaId " +
            "AND p.ativo = true " +
            "ORDER BY p.nome ASC")
    List<Produto> buscarProdutosCardapio(@Param("categoriaId") int categoriaId);

    @Query("SELECT p FROM Produto p " +
            "WHERE p.categoria.id = :categoriaId " +
            "ORDER BY p.nome ASC")
    List<Produto> buscarProdutos(@Param("categoriaId") int categoriaId);

    Produto getProdutoById(int id);
}