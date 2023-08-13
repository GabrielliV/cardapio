package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.*;

import java.util.List;

public interface PedidoService {
    PedidoDto pedido(int id);

    List<PedidosDto> pedidos(int estabelecimentoId);

    PedidoMesaDto buscarPedidoMesa(int mesa);

    PedidoMesaDto buscarPedidoCod(String cod);

    void finalizaPedido(PedidoCarrinhoDto pedidoCarrinhoDto);

}
