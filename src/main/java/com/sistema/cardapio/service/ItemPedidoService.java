package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.PedidoItemDto;
import com.sistema.cardapio.dto.RelatorioPratosDto;
import com.sistema.cardapio.model.ItemPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemPedidoService {
    List<ItemPedido> itemPedidoPorPedido(int pedidoId);

    ItemPedido createItem(PedidoItemDto pedidoItemDto, int pedidoId);

    Page<RelatorioPratosDto> pratosSolicitados(String order);
}
