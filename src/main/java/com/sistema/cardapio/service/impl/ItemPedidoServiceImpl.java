package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.dto.PedidoItemDto;
import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.model.Produto;
import com.sistema.cardapio.repository.ItemPedidoRepository;
import com.sistema.cardapio.service.ItemPedidoService;
import com.sistema.cardapio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    private final ProdutoService produtoService;

    @Autowired
    public ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository, ProdutoService produtoService) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoService = produtoService;
    }

    @Override
    public List<ItemPedido> itemPedidoPorPedido(int id_pedido) {
        return itemPedidoRepository.getItemPedidoById_pedido(id_pedido);
    }

    @Override
    public ItemPedido createItem(PedidoItemDto pedidoItemDto, int pedidoId) {
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setId_pedido(pedidoId);
        itemPedido.setQtde(pedidoItemDto.getQtde());

        Produto produto = produtoService.produto(pedidoItemDto.getProdutoId());

        itemPedido.setProduto(produto);
        itemPedido.setTotal(pedidoItemDto.getQtde() * produto.getPreco());

        itemPedidoRepository.save(itemPedido);

        return itemPedido;
    }
}
