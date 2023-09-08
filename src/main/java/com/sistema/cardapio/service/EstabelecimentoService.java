package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.EstabalecimentoDto;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.model.Pedido;

import java.util.List;

public interface EstabelecimentoService {
    List<Estabelecimento> listarEstabelecimento();

    EstabalecimentoDto loginEstabelecimento(String login, int identificador);
}
