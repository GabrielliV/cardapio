package com.sistema.cardapio.services.impl;

import com.sistema.cardapio.dto.*;
import com.sistema.cardapio.model.*;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.repository.PedidoRepository;
import com.sistema.cardapio.service.ContaService;
import com.sistema.cardapio.service.ItemPedidoService;
import com.sistema.cardapio.service.impl.PedidoServiceImpl;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ContaService contaService;

    @Mock
    private ItemPedidoService itemPedidoService;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void pedidoTest() {
        int pedidoId = 1;
        Pedido mockPedido = gePedidoMock();

        List<ItemPedido> mockItensPedido = new ArrayList<>();

        Mockito.when(pedidoRepository.findPedidoById(pedidoId)).thenReturn(mockPedido);
        Mockito.when(itemPedidoService.itemPedidoPorPedido(pedidoId)).thenReturn(mockItensPedido);

        PedidoDto result = pedidoService.pedido(pedidoId);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Obs", result.getObservacao());
        assertEquals(LocalDateTime.of(2023, 1, 1, 11, 0), result.getHora_pedido());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), result.getHora_entrega());

        Mockito.verify(pedidoRepository).findPedidoById(pedidoId);
        Mockito.verify(itemPedidoService).itemPedidoPorPedido(pedidoId);
    }

    @Test
    void pedidosTest() {
        int estabelecimentoId = 1;
        List<Pedido> mockPedidos = getPedidosMock();

        Mockito.when(pedidoRepository.buscarPedidosEstabelecimento(estabelecimentoId)).thenReturn(mockPedidos);

        List<PedidosDto> result = pedidoService.pedidos(estabelecimentoId);

        assertNotNull(result);

        PedidosDto pedidosDto = result.get(0);
        Pedido pedido = mockPedidos.get(0);

        assertEquals(pedido.getId(), pedidosDto.getId());
        assertEquals(pedido.getHora_pedido().toLocalTime(), pedidosDto.getHora_pedido());
        assertEquals(pedido.getConta().getMesa().getId(), pedidosDto.getMesa());

        Mockito.verify(pedidoRepository).buscarPedidosEstabelecimento(estabelecimentoId);
    }

    @Test
    void buscarPedidoMesa() {
        int mesaId = 1;
        List<Pedido> mockPedidos = getPedidosMock();

        Mockito.when(pedidoRepository.buscarPedidoMesa(mesaId)).thenReturn(mockPedidos);
        Mockito.when(itemPedidoService.itemPedidoPorPedido(Mockito.anyInt())).thenReturn(getItemPedidoMock());

        PedidoMesaDto result = pedidoService.buscarPedidoMesa(mesaId);

        assertNotNull(result);

        Pedido pedido = mockPedidos.get(0);

        assertEquals(pedido.getConta().getCod(), result.getCod());
        assertEquals(pedido.getConta().getTotal(), result.getTotal());

        Mockito.verify(pedidoRepository).buscarPedidoMesa(mesaId);
        Mockito.verify(itemPedidoService, Mockito.atLeastOnce()).itemPedidoPorPedido(Mockito.anyInt());
    }

    @Test
    void buscarPedidoCod() {
        String cod = "123";
        List<Pedido> mockPedidos = getPedidosMock();

        Mockito.when(pedidoRepository.buscarPedidoCod(cod)).thenReturn(mockPedidos);
        Mockito.when(itemPedidoService.itemPedidoPorPedido(Mockito.anyInt())).thenReturn(getItemPedidoMock());

        PedidoMesaDto result = pedidoService.buscarPedidoCod(cod);

        assertNotNull(result);

        Pedido pedido = mockPedidos.get(0);

        assertEquals(pedido.getConta().getCod(), result.getCod());
        assertEquals(pedido.getConta().getTotal(), result.getTotal());

        Mockito.verify(pedidoRepository).buscarPedidoCod(cod);
        Mockito.verify(itemPedidoService, Mockito.atLeastOnce()).itemPedidoPorPedido(Mockito.anyInt());
    }

    @Test
    void tempoMedio() {
        int estabelecimentoId = 1;
        List<Pedido> mockPedidos = getPedidosMock();
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(pedidoRepository.buscarPedidosEntregues(estabelecimentoId, pageable)).thenReturn(mockPedidos);

        String result = pedidoService.tempoMedio(estabelecimentoId);

        assertNotNull(result);
        Mockito.verify(pedidoRepository).buscarPedidosEntregues(estabelecimentoId, pageable);
    }

    @Test
    void solicitaPedidoContaNullTest() {
        PedidoCarrinhoDto pedidoCarrinhoDto = getPedidoCarrinhoDtoMock();
        Pedido mockPedido = gePedidoMock();
        mockPedido.setConta(null);

        Mockito.when(contaService.buscaContaCod(pedidoCarrinhoDto.getCod(), pedidoCarrinhoDto.getMesaId()))
                .thenReturn(mockPedido.getConta());
        Mockito.when(contaService.criarConta(pedidoCarrinhoDto.getMesaId(), pedidoCarrinhoDto.getCod()))
                .thenReturn(mockPedido.getConta());
        Mockito.when(itemPedidoService.createItem(pedidoCarrinhoDto.getPedidoItem().get(0), mockPedido.getId()))
                .thenReturn(getItemPedidoMock().get(0));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(mockPedido);

        pedidoService.solicitaPedido(pedidoCarrinhoDto);

        Mockito.verify(contaService, Mockito.atLeastOnce()).criarConta(Mockito.anyInt(), Mockito.anyString());
        Mockito.verify(itemPedidoService, Mockito.atLeastOnce()).createItem(Mockito.any(), Mockito.anyInt());
        Mockito.verify(contaService, Mockito.atLeastOnce()).atualizaTotal(Mockito.any(), Mockito.anyDouble());
    }

    @Test
    void solicitaPedidoComContaTest() {
        PedidoCarrinhoDto pedidoCarrinhoDto = getPedidoCarrinhoDtoMock();
        Pedido mockPedido = gePedidoMock();

        Mockito.when(contaService.buscaContaCod(pedidoCarrinhoDto.getCod(), pedidoCarrinhoDto.getMesaId()))
                .thenReturn(mockPedido.getConta());
        Mockito.when(contaService.criarConta(pedidoCarrinhoDto.getMesaId(), pedidoCarrinhoDto.getCod()))
                .thenReturn(mockPedido.getConta());
        Mockito.when(itemPedidoService.createItem(pedidoCarrinhoDto.getPedidoItem().get(0), mockPedido.getId()))
                .thenReturn(getItemPedidoMock().get(0));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(mockPedido);

        pedidoService.solicitaPedido(pedidoCarrinhoDto);

        Mockito.verify(contaService, Mockito.atLeastOnce()).buscaContaCod(Mockito.anyString(), Mockito.anyInt());
        Mockito.verify(itemPedidoService, Mockito.atLeastOnce()).createItem(Mockito.any(), Mockito.anyInt());
        Mockito.verify(contaService, Mockito.atLeastOnce()).atualizaTotal(Mockito.any(), Mockito.anyDouble());
    }

    @Test
    void finalizaPedido() {
        int pedidoId = 1;
        Pedido mockPedido = getPedidosMock().get(0);

        Mockito.when(pedidoRepository.findPedidoById(pedidoId)).thenReturn(mockPedido);
        Mockito.when(pedidoRepository.save(Mockito.any())).thenReturn(mockPedido);

        pedidoService.finalizaPedido(pedidoId);

        Mockito.verify(pedidoRepository).findPedidoById(pedidoId);
        Mockito.verify(pedidoRepository).save(Mockito.any());
    }

    private List<Pedido> getPedidosMock() {
        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(gePedidoMock());

        return pedidos;
    }

    private Pedido gePedidoMock() {
        Estabelecimento estabelecimento = new Estabelecimento(1, "estabelecimento", "estabelecimento", 123);

        Mesa mesa = new Mesa(1, 1, true, estabelecimento);

        Conta conta = new Conta(1, true, 100, "123", mesa);

        return new Pedido(
                1,
                "Obs",
                LocalDateTime.of(2023, 1, 1, 11, 0),
                LocalDateTime.of(2023, 1, 1, 12, 0),
                conta);
    }

    private List<ItemPedido> getItemPedidoMock() {
        List<ItemPedido> itens = new ArrayList<>();

        Categoria categoria = new Categoria(1 , "Categoria1");

        Produto produto = new Produto(1, "Suco", "Suco natural", 20, null, true, categoria);

        ItemPedido item = new ItemPedido(1, 1, 100, 1, produto);

        itens.add(item);

        return itens;
    }

    private PedidoCarrinhoDto getPedidoCarrinhoDtoMock() {
        List<PedidoItemDto> pedidoItensDto = new ArrayList<>();

        PedidoItemDto pedidoItemDto = new PedidoItemDto(1, 1);

        pedidoItensDto.add(pedidoItemDto);

        return new PedidoCarrinhoDto(1, "123", null, pedidoItensDto);
    }

}
