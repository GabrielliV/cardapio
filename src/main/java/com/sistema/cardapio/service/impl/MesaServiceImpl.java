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
        List<Mesa> mesas = mesaRepository.getMesaByEstabelecimentoIdOrderByAtivoDesc(estabelecimentoId);

        for (Mesa mesa: mesas) {
            MesasDto mesaDto = new MesasDto();
            mesaDto.setId(mesa.getId());
            mesaDto.setMesa(mesa.getMesa());

            if (mesa.getAtivo() == true) {
                mesaDto.setAtivo("Inativar");
            } else {
                mesaDto.setAtivo("Ativar");
            }

            Conta conta = contaRepository.findLastContaByMesaIdAndStatus(mesa.getId(), true);
            if (conta != null) {
                mesaDto.setStatus("Ocupada");
            } else {
                mesaDto.setStatus("Liberada");
            }

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
        mesaNova.setAtivo(true);

        mesaRepository.save(mesaNova);
    }

    @Override
    public void ativaInativaMesa(int mesaId, String status) {
        Mesa mesa = mesaRepository.getMesaById(mesaId);

        if (status.equals("Inativar")) {
            mesa.setAtivo(false);
        } else {
            mesa.setAtivo(true);
        }

        mesaRepository.save(mesa);
    }

}
