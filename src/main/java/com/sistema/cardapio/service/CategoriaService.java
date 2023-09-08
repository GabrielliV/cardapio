package com.sistema.cardapio.service;

import com.sistema.cardapio.model.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listarCategorias();

    List<String> nomesCategoria();
}
