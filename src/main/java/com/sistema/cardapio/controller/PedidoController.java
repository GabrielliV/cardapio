package com.sistema.cardapio.controller;

import com.sistema.cardapio.dto.*;
import com.sistema.cardapio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/cardapio/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pedido/{id}")
    public PedidoDto listarPedido(@PathVariable int id) {
        return pedidoService.pedido(id);
    }

    @GetMapping("/listar/{estabelecimentoId}")
    public List<PedidosDto> listarPedidos(@PathVariable int estabelecimentoId) {
        return pedidoService.pedidos(estabelecimentoId);
    }

    @GetMapping("/mesa/{mesa}")
    public PedidoMesaDto listarPedidosMesa(@PathVariable int mesa) {
        return pedidoService.buscarPedidoMesa(mesa);
    }

    @GetMapping("/cod/{cod}")
    public PedidoMesaDto listarPedidosCod(@PathVariable String cod) {
        return pedidoService.buscarPedidoCod(cod);
    }

    @GetMapping("tempomedio/{estabelecimentoId}")
    public String tempoMedio(@PathVariable int estabelecimentoId) {
        return pedidoService.tempoMedio(estabelecimentoId);
    }

    @PostMapping("/solicitar")
    public void solicitaPedido(@RequestBody PedidoCarrinhoDto pedidoCarrinhoDto) {
        pedidoService.solicitaPedido(pedidoCarrinhoDto);
    }

    @PostMapping("/finalizar/{pedidoId}")
    public void finalizaPedido(@PathVariable int pedidoId) {
        pedidoService.finalizaPedido(pedidoId);
    }

}
