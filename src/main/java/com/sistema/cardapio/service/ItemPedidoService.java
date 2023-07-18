package com.sistema.cardapio.service;

import com.sistema.cardapio.model.ItemPedido;

import java.util.List;

public interface ItemPedidoService {
    List<ItemPedido> itemPedidoPorPedido(int pedidoId);
}
