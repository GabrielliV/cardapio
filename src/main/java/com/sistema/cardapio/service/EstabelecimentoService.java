package com.sistema.cardapio.service;

import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.model.Pedido;

import java.util.List;

public interface EstabelecimentoService {
    List<Estabelecimento> listarEstabelecimento();

    Estabelecimento validaEstabelecimento(String login, int identificador);
}
