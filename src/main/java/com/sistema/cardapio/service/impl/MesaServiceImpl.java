package com.sistema.cardapio.service.impl;

import com.sistema.cardapio.dto.MesasDto;
import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.model.Mesa;
import com.sistema.cardapio.repository.ContaRepository;
import com.sistema.cardapio.repository.EstabelecimentoRepository;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.service.MesaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ContaRepository contaRepository;

    public MesaServiceImpl(MesaRepository mesaRepository, EstabelecimentoRepository estabelecimentoRepository, ContaRepository contaRepository) {
        this.mesaRepository = mesaRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.contaRepository = contaRepository;
    }

    @Override
    public Mesa validaMesa(String login, int identificador, int mesa) {
        Estabelecimento estabelecimento =
                estabelecimentoRepository.getEstabelecimentoByLoginAndIdentificador(login, identificador);
        return mesaRepository.getMesaByEstabelecimentoIdAndMesa(estabelecimento.getId(), mesa);
    }

    @Override
    public List<MesasDto> mesas(int estabelecimentoId) {
        List<MesasDto> mesasDtos = new ArrayList<>();
        List<Mesa> mesas = mesaRepository.getMesaByEstabelecimentoId(estabelecimentoId);

        for (Mesa mesa: mesas) {
            MesasDto mesaDto = new MesasDto();
            mesaDto.setId(mesa.getId());
            mesaDto.setMesa(mesa.getMesa());

            Conta conta = contaRepository.findLastContaByMesaIdAndStatus(mesa.getId(), true);
            mesaDto.setStatus(conta != null);

            mesasDtos.add(mesaDto);
        }

        return mesasDtos;
    }

    @Override
    public void criaMesa(int estabelecimentoId, int mesa) {
        Mesa mesaNova = new Mesa();
        Estabelecimento estabelecimento = estabelecimentoRepository.getEstabelecimentoById(estabelecimentoId);

        mesaNova.setMesa(mesa);
        mesaNova.setEstabelecimento(estabelecimento);

        mesaRepository.save(mesaNova);
    }

}
