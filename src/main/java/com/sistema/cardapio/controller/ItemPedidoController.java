package com.sistema.cardapio.controller;

import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio/itens")
public class ItemPedidoController {

    private final ItemPedidoService pedidoService;

    @Autowired
    public ItemPedidoController(ItemPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("{pedidoId}")
    public List<ItemPedido> listarPedidos(@PathVariable int pedidoId) {
        return pedidoService.itemPedidoPorPedido(pedidoId);
    }
}
