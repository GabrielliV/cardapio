package com.sistema.cardapio.services.impl;

import com.sistema.cardapio.dto.ProdutoDto;
import com.sistema.cardapio.model.Categoria;
import com.sistema.cardapio.model.Produto;
import com.sistema.cardapio.repository.CategoriaRepository;
import com.sistema.cardapio.repository.ProdutoRepository;
import com.sistema.cardapio.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void produtosPorCategoriaTest() {
        when(produtoRepository.buscarProdutosCardapio(anyInt())).thenReturn(getProdutosMock());

        List<Produto> produtos = produtoService.produtosPorCategoria(1);

        assertEquals(1, produtos.size());
    }

    @Test
    public void criaProdutoTest() {
        ProdutoDto produtoDto = getProdutoDtoMock();
        Produto produtoMock = getProdutoMock();
        Categoria categoriaMock = produtoMock.getCategoria();

        when(categoriaRepository.getCategoriaById(anyInt())).thenReturn(categoriaMock);

        when(produtoRepository.save(any())).thenReturn(produtoMock);

        int produtoId = produtoService.criaProduto(produtoDto);

        assertEquals(produtoMock.getId(), produtoId);

        verify(categoriaRepository, times(1)).getCategoriaById(anyInt());
        verify(produtoRepository, times(1)).save(any());
    }

    @Test
    void ativaInativaProdutoTest() {
        when(produtoRepository.getProdutoById(anyInt())).thenReturn(new Produto());

        produtoService.ativaInativaProduto(1, true);

        verify(produtoRepository, times(1)).save(any());
    }

    @Test
    void alteraProdutoTest() {
        when(produtoRepository.getProdutoById(anyInt())).thenReturn(getProdutoMock());
        when(produtoRepository.save(any())).thenReturn(getProdutoMock());

        ProdutoDto produtoDto = getProdutoDtoMock();
        int result = produtoService.alteraProduto(1, produtoDto);

        assertEquals(1, result);
    }

    @Test
    void deletaProdutoTest() {
        when(produtoRepository.getProdutoById(anyInt())).thenReturn(new Produto());

        produtoService.deletaProduto(1);

        verify(produtoRepository, times(1)).delete(any());
    }

    private ProdutoDto getProdutoDtoMock() {
        return new ProdutoDto("Suco", "Suco natural", 10, null, 1);
    }

    private Produto getProdutoMock() {
        Categoria categoria = new Categoria(1 , "Categoria1");

        return new Produto(1, "Suco", "Suco natural", 20, null, true, categoria);
    }

    private List<Produto> getProdutosMock() {
        List<Produto> produtos = new ArrayList<>();

        produtos.add(getProdutoMock());

        return produtos;
    }
}
