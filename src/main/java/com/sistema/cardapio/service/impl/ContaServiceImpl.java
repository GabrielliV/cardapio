package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Mesa;
import com.sistema.cardapio.repository.ContaRepository;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.service.ContaService;
import org.springframework.stereotype.Service;

@Service
public class ContaServiceImpl implements ContaService {
    private final ContaRepository contaRepository;

    private final MesaRepository mesaRepository;

    public ContaServiceImpl(ContaRepository contaRepository, MesaRepository mesaRepository) {
        this.contaRepository = contaRepository;
        this.mesaRepository = mesaRepository;
    }

    @Override
    public Conta buscaContaCod(String cod, int mesaId) {
        return contaRepository.getContaByCodAndMesa_Id(cod, mesaId);
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
}
