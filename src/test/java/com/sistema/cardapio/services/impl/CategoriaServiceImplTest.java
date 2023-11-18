package com.sistema.cardapio.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.sistema.cardapio.model.Categoria;
import com.sistema.cardapio.repository.CategoriaRepository;
import com.sistema.cardapio.service.impl.CategoriaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaServiceImplTest {

    private CategoriaRepository categoriaRepository;
    private CategoriaServiceImpl categoriaService;

    @Before
    public void setUp() {
        categoriaRepository = mock(CategoriaRepository.class);
        categoriaService = new CategoriaServiceImpl(categoriaRepository);
    }

    @Test
    public void listarCategoriasTest() {
        when(categoriaRepository.findAll()).thenReturn(getCategoriasMock());

        List<Categoria> result = categoriaService.listarCategorias();

        verify(categoriaRepository, times(1)).findAll();

        assertEquals(getCategoriasMock(), result);
    }

    private List<Categoria> getCategoriasMock() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(1, "Categoria1"));
        categorias.add(new Categoria(2, "Categoria2"));

        return categorias;
    }
}
