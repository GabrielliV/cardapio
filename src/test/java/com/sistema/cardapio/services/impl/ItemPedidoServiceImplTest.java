package com.sistema.cardapio.services.impl;

import com.sistema.cardapio.dto.PedidoItemDto;
import com.sistema.cardapio.dto.RelatorioPratosDto;
import com.sistema.cardapio.model.Categoria;
import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.model.Produto;
import com.sistema.cardapio.repository.ItemPedidoRepository;
import com.sistema.cardapio.service.ProdutoService;
import com.sistema.cardapio.service.impl.ItemPedidoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemPedidoServiceImplTest {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ItemPedidoServiceImpl itemPedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itemPedidoPorPedidoTest() {
        int idPedido = 1;
        List<ItemPedido> mockItensPedido = getItensPedidoMock();
        Mockito.when(itemPedidoRepository.getItemPedidoById_pedido(idPedido)).thenReturn(mockItensPedido);

        List<ItemPedido> result = itemPedidoService.itemPedidoPorPedido(idPedido);

        assertNotNull(result);
        assertEquals(getItensPedidoMock().size(), result.size());
        assertEquals(getItensPedidoMock().get(0).getId_pedido(), result.get(0).getId_pedido());
        assertEquals(getItensPedidoMock().get(1).getId_pedido(), result.get(1).getId_pedido());
        assertEquals(getItensPedidoMock().get(0).getQtde(), result.get(0).getQtde());
        assertEquals(getItensPedidoMock().get(1).getQtde(), result.get(1).getQtde());
        Mockito.verify(itemPedidoRepository).getItemPedidoById_pedido(idPedido);
    }

    @Test
    void createItemTest() {
        PedidoItemDto pedidoItemDto = getPedidoItemDtoMock();

        int pedidoId = 1;
        Produto mockProduto = getProdutoMock();
        Mockito.when(produtoService.produto(pedidoItemDto.getProdutoId())).thenReturn(mockProduto);

        ItemPedido result = itemPedidoService.createItem(pedidoItemDto, pedidoId);

        assertNotNull(result);
        assertEquals(pedidoId, result.getId_pedido());
        assertEquals(pedidoItemDto.getQtde(), result.getQtde());
        assertEquals(mockProduto, result.getProduto());
        Mockito.verify(itemPedidoRepository).save(result);
    }

    @Test
    void pratosSolicitadosTest() {
        String order = "MENOS";
        PageRequest topTen = PageRequest.of(0, 10);
        Page<RelatorioPratosDto> mockPage = Mockito.mock(Page.class);
        Mockito.when(itemPedidoRepository.pratosMenosSolicitados(topTen)).thenReturn(mockPage);

        Page<RelatorioPratosDto> result = itemPedidoService.pratosSolicitados(order);

        assertNotNull(result);
        Mockito.verify(itemPedidoRepository).pratosMenosSolicitados(topTen);
    }

    private List<ItemPedido> getItensPedidoMock() {
        Categoria categoria = new Categoria(1 , "Categoria1");

        Produto produto = new Produto(1, "Suco", "Suco natural", 20, null, true, categoria);

        return Arrays.asList(
                new ItemPedido(1, 2, 10.0, 1, produto),
                new ItemPedido(2, 3, 10.0, 2, produto)
        );
    }

    private Produto getProdutoMock() {
        Categoria categoria = new Categoria(1 , "Categoria1");

        return new Produto(1, "Suco", "Suco natural", 20, null, true, categoria);
    }

    private PedidoItemDto getPedidoItemDtoMock() {
        PedidoItemDto pedidoItemDto = new PedidoItemDto();

        pedidoItemDto.setProdutoId(1);
        pedidoItemDto.setQtde(2);

        return pedidoItemDto;
    }
}
