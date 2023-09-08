package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.*;

import java.time.Duration;
import java.util.List;

public interface PedidoService {
    PedidoDto pedido(int id);

    List<PedidosDto> pedidos(int estabelecimentoId);

    PedidoMesaDto buscarPedidoMesa(int mesa);

    PedidoMesaDto buscarPedidoCod(String cod);

    String tempoMedio (int estabelecimentoId);

    void solicitaPedido(PedidoCarrinhoDto pedidoCarrinhoDto);

    void finalizaPedido(int pedidoId);

}
