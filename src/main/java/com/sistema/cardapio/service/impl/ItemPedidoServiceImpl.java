package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.repository.ItemPedidoRepository;
import com.sistema.cardapio.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public List<ItemPedido> itemPedidoPorPedido(int id_pedido) {
        return itemPedidoRepository.getItemPedidoById_pedido(id_pedido);
    }
}
