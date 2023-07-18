package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.PedidoDto;
import com.sistema.cardapio.dto.PedidosDto;

import java.util.List;

public interface PedidoService {
    PedidoDto pedido(int id);

    List<PedidosDto> pedidos(int estabelecimentoId);
}
