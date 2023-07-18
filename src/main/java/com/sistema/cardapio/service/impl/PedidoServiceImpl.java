package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.dto.PedidoDto;
import com.sistema.cardapio.dto.PedidosDto;
import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.model.Pedido;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.repository.PedidoRepository;
import com.sistema.cardapio.service.ItemPedidoService;
import com.sistema.cardapio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;

    private final ItemPedidoService itemPedidoService;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, MesaRepository mesaRepository, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
        this.itemPedidoService = itemPedidoService;
    }

    @Override
    public PedidoDto pedido(int id) {
        PedidoDto pedidoDto = new PedidoDto();
        Pedido pedido = pedidoRepository.findPedidoById(id);
        List<ItemPedido> itensPedido = itemPedidoService.itemPedidoPorPedido(id);

        pedidoDto.setId(pedido.getId());
        pedidoDto.setObservacao(pedido.getObservacao());
        pedidoDto.setHora_pedido(pedido.getHora_pedido());
        pedidoDto.setItemPedido(itensPedido);
        return pedidoDto;
    }

    @Override
    public List<PedidosDto> pedidos(int estabelecimento) {
        List<PedidosDto> pedidosDtos = new ArrayList<>();
        List<Pedido> pedidos = pedidoRepository.buscarPedidosEstabelecimento(estabelecimento);

        for (Pedido pedido: pedidos) {
            PedidosDto pedidosDto = new PedidosDto();
            pedidosDto.setId(pedido.getId());
            pedidosDto.setHora_pedido(pedido.getHora_pedido().toLocalTime());
            pedidosDto.setMesa_id(pedido.getConta().getMesa().getMesa());

            pedidosDtos.add(pedidosDto);
        }

        return pedidosDtos;
    }
}
