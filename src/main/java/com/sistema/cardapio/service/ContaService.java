package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.ContaDto;
import com.sistema.cardapio.model.Conta;

public interface ContaService {
    Conta buscaContaCod(String cod, int mesaId);

    Conta criarConta(int idMesa, String cod);

    void atualizaTotal(Conta conta, double valor);

    void finalizaMesa(int mesaId);

    void finalizaCod(String cod);
}
