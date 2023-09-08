package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria getCategoriaById(@Param("categoriaId") int categoriaId);

}
