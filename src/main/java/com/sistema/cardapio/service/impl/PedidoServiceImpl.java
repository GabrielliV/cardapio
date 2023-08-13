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
import org.springframework.stereotype.Service;

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

    @Override
    public PedidoMesaDto buscarPedidoMesa(int mesa) {
        List<Pedido> pedidos = pedidoRepository.buscarPedidoMesa(mesa);

        return retornaPedidos(pedidos);
    }

    @Override
    public PedidoMesaDto buscarPedidoCod(String cod) {
        List<Pedido> pedidos = pedidoRepository.buscarPedidoCod(cod);

        return retornaPedidos(pedidos);
    }

    @Override
    public void finalizaPedido(PedidoCarrinhoDto pedidoCarrinhoDto) {
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

    private PedidoMesaDto retornaPedidos(List<Pedido> pedidos) {
        PedidoMesaDto pedidoMesaDto = new PedidoMesaDto();
        double valor = 0;

        for (Pedido pedido: pedidos) {
            List<ItemPedido> itens = itemPedidoService.itemPedidoPorPedido(pedido.getId());
            List<ItemPedidoDto> itensPedidoDto = new ArrayList<>();

            for (ItemPedido item : itens) {
                ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
                itemPedidoDto.setPedido_id(item.getId_pedido());
                itemPedidoDto.setNome(item.getProduto().getNome());
                itemPedidoDto.setQtde(item.getQtde());
                itemPedidoDto.setValor(item.getTotal());

                itensPedidoDto.add(itemPedidoDto);
                valor = valor + item.getTotal();
            }

            pedidoMesaDto.setItensDto(itensPedidoDto);
            pedidoMesaDto.setTotal(valor);
            pedidoMesaDto.setCod(pedidos.get(0).getConta().getCod());
        }

        return pedidoMesaDto;
    }

    public Pedido createPedido (Conta conta, String observacao) {
        Pedido pedido = new Pedido();

        pedido.setConta(conta);
        pedido.setHora_pedido(LocalDateTime.now());
        pedido.setObservacao(observacao);

        pedidoRepository.save(pedido);

        return pedido;
    }
}
