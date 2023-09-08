package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.model.Categoria;
import com.sistema.cardapio.repository.CategoriaRepository;
import com.sistema.cardapio.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<String> nomesCategoria() {
        List<Categoria> categorias = listarCategorias();

        List<String> nomes = new ArrayList<>();;

        for (Categoria categoria : categorias) {
            nomes.add(categoria.getNome());
        }

        return nomes;
    }
}
