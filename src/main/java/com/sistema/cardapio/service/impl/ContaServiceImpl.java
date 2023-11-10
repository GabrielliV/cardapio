package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.dto.ContaDto;
import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Mesa;
import com.sistema.cardapio.repository.ContaRepository;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.repository.PedidoRepository;
import com.sistema.cardapio.service.ContaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {
    private final ContaRepository contaRepository;

    private final MesaRepository mesaRepository;

    private final PedidoRepository pedidoRepository;

    public ContaServiceImpl(ContaRepository contaRepository, MesaRepository mesaRepository, PedidoRepository pedidoRepository) {
        this.contaRepository = contaRepository;
        this.mesaRepository = mesaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Conta buscaContaCod(String cod, int mesaId) {
        return contaRepository.getContaByCodAndMesa_IdAndStatus(cod, mesaId, true);
    }

    @Override
    public Conta criarConta(int idMesa, String cod) {
        Conta novaConta = new Conta();

        Mesa mesa = mesaRepository.getMesaById(idMesa);
        novaConta.setMesa(mesa);
        novaConta.setCod(cod);
        novaConta.setStatus(true);
        novaConta.setTotal(0);

        return contaRepository.save(novaConta);
    }

    @Override
    public void atualizaTotal(Conta conta, double valor) {
        Conta contaUpdate = conta;

        contaUpdate.setTotal(contaUpdate.getTotal() + valor);

        contaRepository.save(conta);
    }

    @Override
    public void finalizaMesa(int mesaId) {
        List<Conta> contas = contaRepository.getContaByMesaId(mesaId);

        for (Conta conta : contas) {
            conta.setStatus(false);
            contaRepository.save(conta);
        }
    }

    @Override
    public void finalizaCod(String cod) {
        List<Conta> contas = contaRepository.getContaByCod(cod);

        for (Conta conta : contas) {
            conta.setStatus(false);
            contaRepository.save(conta);
        }
    }

}
