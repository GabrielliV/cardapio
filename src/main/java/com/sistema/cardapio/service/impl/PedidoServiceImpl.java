package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.dto.*;
import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.model.Pedido;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.repository.PedidoRepository;
import com.sistema.cardapio.service.ContaService;
import com.sistema.cardapio.service.ItemPedidoService;
import com.sistema.cardapio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;

    private final ContaService contaService;

    private final ItemPedidoService itemPedidoService;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, MesaRepository mesaRepository, ContaService contaService, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
        this.contaService = contaService;
        this.itemPedidoService = itemPedidoService;
    }

    @Override
    public PedidoDto pedido(int id) {
        PedidoDto pedidoDto = new PedidoDto();
        Pedido pedido = pedidoRepository.findPedidoById(id);
        List<ItemPedido> itensPedido = itemPedidoService.itemPedidoPorPedido(id);

        pedidoDto.setMesa(pedido.getConta().getMesa().getMesa());
        pedidoDto.setId(pedido.getId());
        pedidoDto.setObservacao(pedido.getObservacao());
        pedidoDto.setHora_pedido(pedido.getHora_pedido());
        pedidoDto.setHora_entrega(pedido.getHora_entrega());
        pedidoDto.setItemPedido(itensPedido);
        return pedidoDto;
    }

    @Override
    public List<PedidosDto> pedidos(int estabelecimentoId) {
        List<PedidosDto> pedidosDtos = new ArrayList<>();
        List<Pedido> pedidos = pedidoRepository.buscarPedidosEstabelecimento(estabelecimentoId);

        for (Pedido pedido: pedidos) {
            PedidosDto pedidosDto = new PedidosDto();
            pedidosDto.setId(pedido.getId());
            pedidosDto.setHora_pedido(pedido.getHora_pedido().toLocalTime());
            pedidosDto.setMesa(pedido.getConta().getMesa().getMesa());

            pedidosDtos.add(pedidosDto);
        }

        return pedidosDtos;
    }

    @Override
    public PedidoMesaDto buscarPedidoMesa(int mesaId) {
        List<Pedido> pedidos = pedidoRepository.buscarPedidoMesa(mesaId);

        return retornaPedidos(pedidos);
    }

    @Override
    public PedidoMesaDto buscarPedidoCod(String cod) {
        List<Pedido> pedidos = pedidoRepository.buscarPedidoCod(cod);

        return retornaPedidos(pedidos);
    }

    @Override
    public String tempoMedio(int estabelecimentoId) {
        Pageable pageable = PageRequest.of(0, 10);

        List<Pedido> pedidos = pedidoRepository.buscarPedidosEntregues(estabelecimentoId, pageable);

        long totalDuracao = 0;

        for (Pedido pedido : pedidos) {
            Duration duracaoPedido = calcularTempoEntrega(pedido.getHora_pedido(), pedido.getHora_entrega());
            totalDuracao += duracaoPedido.toMillis();
        }

        if (!pedidos.isEmpty()) {
            Duration tempoMedio = Duration.ofMillis(totalDuracao / pedidos.size());

            return String.format("%d:%d", tempoMedio.toHours(), tempoMedio.toMinutes());
        } else {
            return "00:00";
        }
    }

    @Override
    public void solicitaPedido(PedidoCarrinhoDto pedidoCarrinhoDto) {
        Conta conta = contaService.buscaContaCod(pedidoCarrinhoDto.getCod(), pedidoCarrinhoDto.getMesaId());

        if (conta == null) {
            conta = contaService.criarConta(pedidoCarrinhoDto.getMesaId(), pedidoCarrinhoDto.getCod());
        }

        double valor = 0;

        Pedido pedido = createPedido(conta, pedidoCarrinhoDto.getObservacao());

        for (PedidoItemDto itemPedidoDto : pedidoCarrinhoDto.getPedidoItem()) {
            ItemPedido itemPedido = itemPedidoService.createItem(itemPedidoDto, pedido.getId());

            valor = valor + itemPedido.getTotal();
        }

        contaService.atualizaTotal(conta, valor);

    }

    @Override
    public void finalizaPedido(int pedidoId) {
        Pedido pedido = pedidoRepository.findPedidoById(pedidoId);

        if (pedido != null) {
            pedido.setHora_entrega(LocalDateTime.now());
            pedidoRepository.save(pedido);
        }
    }

    private PedidoMesaDto retornaPedidos(List<Pedido> pedidos) {
        PedidoMesaDto pedidoMesaDto = new PedidoMesaDto();
        List<ItemPedidoDto> itensPedidoDto = new ArrayList<>();
        double valor = 0;

        for (Pedido pedido: pedidos) {
            List<ItemPedido> itens = itemPedidoService.itemPedidoPorPedido(pedido.getId());

            for (ItemPedido item : itens) {
                ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
                itemPedidoDto.setItemId(item.getId());
                itemPedidoDto.setNome(item.getProduto().getNome());
                itemPedidoDto.setQtde(item.getQtde());
                itemPedidoDto.setValor(item.getTotal());

                itensPedidoDto.add(itemPedidoDto);
                valor = valor + (item.getTotal() * item.getQtde());
            }

            pedidoMesaDto.setItensDto(itensPedidoDto);
            pedidoMesaDto.setCod(pedidos.get(0).getConta().getCod());
        }

        pedidoMesaDto.setTotal(valor);

        return pedidoMesaDto;
    }

    public Pedido createPedido (Conta conta, String observacao) {
        Pedido pedido = new Pedido();

        pedido.setConta(conta);
        pedido.setHora_pedido(LocalDateTime.now());
        pedido.setObservacao(observacao);

        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    private Duration calcularTempoEntrega(LocalDateTime hora_pedido, LocalDateTime hora_entrega) {
        return Duration.between(hora_pedido, hora_entrega);
    }
}
