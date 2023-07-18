package com.sistema.cardapio.controller;

import com.sistema.cardapio.dto.PedidoDto;
import com.sistema.cardapio.dto.PedidosDto;
import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.model.Pedido;
import com.sistema.cardapio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("{id}")
    public PedidoDto listarPedido(@PathVariable int id) {
        return pedidoService.pedido(id);
    }

    @GetMapping("listar/{estabelecimentoId}")
    public List<PedidosDto> listarPedidos(@PathVariable int estabelecimentoId) {
        return pedidoService.pedidos(estabelecimentoId);
    }
}
