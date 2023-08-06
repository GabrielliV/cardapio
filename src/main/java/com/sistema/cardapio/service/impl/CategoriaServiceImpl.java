package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.model.Categoria;
import com.sistema.cardapio.repository.CategoriaRepository;
import com.sistema.cardapio.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
