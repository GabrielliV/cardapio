package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.PedidoDto;
import com.sistema.cardapio.dto.PedidoMesaDto;
import com.sistema.cardapio.dto.PedidosDto;
import com.sistema.cardapio.dto.RelatorioPedidoDto;

import java.util.List;

public interface PedidoService {
    PedidoDto pedido(int id);

    List<PedidosDto> pedidos(int estabelecimentoId);

    PedidoMesaDto buscarPedidoMesa(int mesa);

    PedidoMesaDto buscarPedidoCod(String cod);

}
