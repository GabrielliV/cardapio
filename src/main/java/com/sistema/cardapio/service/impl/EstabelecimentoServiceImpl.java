package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.dto.EstabalecimentoDto;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.repository.EstabelecimentoRepository;
import com.sistema.cardapio.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    public EstabelecimentoServiceImpl(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    public List<Estabelecimento> listarEstabelecimento() {
        return estabelecimentoRepository.findAll();
    }

    @Override
    public EstabalecimentoDto loginEstabelecimento(String login, int identificador) {
        Estabelecimento estabelecimento = estabelecimentoRepository.getEstabelecimentoByLoginAndIdentificador(login, identificador);

        EstabalecimentoDto estabalecimentoDto = new EstabalecimentoDto();

        estabalecimentoDto.setId(estabelecimento.getId());
        estabalecimentoDto.setNome(estabelecimento.getNome());

        return estabalecimentoDto;
    }

}
