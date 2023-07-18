package com.sistema.cardapio.service;

import com.sistema.cardapio.dto.MesasDto;
import com.sistema.cardapio.model.Mesa;

import java.util.List;

public interface MesaService {
    Mesa validaMesa(String login, int identificador, int mesa);

    List<MesasDto> mesas(int estabelecimentoId);
}
