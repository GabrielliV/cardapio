package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.EstabalecimentoDto;
import com.sistema.cardapio.model.Estabelecimento;

import java.util.List;

public interface EstabelecimentoService {
    List<Estabelecimento> listarEstabelecimento();

    EstabalecimentoDto loginEstabelecimento(String login, int identificador);
}
