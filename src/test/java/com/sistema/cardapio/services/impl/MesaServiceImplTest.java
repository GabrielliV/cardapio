package com.sistema.cardapio.services.impl;

import com.sistema.cardapio.dto.MesasDto;
import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.model.Mesa;
import com.sistema.cardapio.repository.ContaRepository;
import com.sistema.cardapio.repository.EstabelecimentoRepository;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.service.impl.MesaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MesaServiceImplTest {

    @Mock
    private MesaRepository mesaRepository;

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private MesaServiceImpl mesaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validaMesaTest() {
        String login = "login1";
        int identificador = 123;
        int mesa = 1;

        Estabelecimento mockEstabelecimento = getEstabelecimentoMock();
        Mockito.when(estabelecimentoRepository.getEstabelecimentoByLoginAndIdentificador(login, identificador)).thenReturn(mockEstabelecimento);

        Mesa mockMesa = new Mesa();
        Mockito.when(mesaRepository.getMesaByEstabelecimentoIdAndMesaOrderByMesa(mockEstabelecimento.getId(), mesa)).thenReturn(mockMesa);

        Mesa result = mesaService.validaMesa(login, identificador, mesa);

        assertNotNull(result);
        Mockito.verify(estabelecimentoRepository).getEstabelecimentoByLoginAndIdentificador(login, identificador);
        Mockito.verify(mesaRepository).getMesaByEstabelecimentoIdAndMesaOrderByMesa(mockEstabelecimento.getId(), mesa);
    }

    @Test
    void mesasTest() {
        int estabelecimentoId = 1;
        List<Mesa> mockMesas = new ArrayList<>();
        mockMesas.add(getMesaMock());
        Mockito.when(mesaRepository.getMesaByEstabelecimentoIdOrderByMesaAsc(estabelecimentoId)).thenReturn(mockMesas);

        Conta mockConta = getContaMock();
        Mockito.when(contaRepository.findLastContaByMesaIdAndStatus(Mockito.anyInt(), Mockito.anyBoolean())).thenReturn(mockConta);

        List<MesasDto> result = mesaService.mesas(estabelecimentoId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Inativar", result.get(0).getAtivo());
        assertEquals("Ocupada", result.get(0).getStatus());
        Mockito.verify(mesaRepository).getMesaByEstabelecimentoIdOrderByMesaAsc(estabelecimentoId);
        Mockito.verify(contaRepository).findLastContaByMesaIdAndStatus(Mockito.anyInt(), Mockito.anyBoolean());
    }

    @Test
    void criaMesaTest() {
        int estabelecimentoId = 1;
        int mesa = 1;
        Estabelecimento mockEstabelecimento = getEstabelecimentoMock();
        Mockito.when(estabelecimentoRepository.getEstabelecimentoById(estabelecimentoId)).thenReturn(mockEstabelecimento);

        mesaService.criaMesa(estabelecimentoId, mesa);

        Mockito.verify(mesaRepository).save(Mockito.any(Mesa.class));
    }

    @Test
    void ativaInativaMesaTest() {
        int mesaId = 1;
        String status = "Inativar";
        Mesa mockMesa = getMesaMock();
        Mockito.when(mesaRepository.getMesaById(mesaId)).thenReturn(mockMesa);

        mesaService.ativaInativaMesa(mesaId, status);

        Mockito.verify(mesaRepository).save(mockMesa);
    }

    private Estabelecimento getEstabelecimentoMock() {
        return new Estabelecimento(1, "Estabelecimento1", "login1", 123);
    }

    private Mesa getMesaMock() {
        return new Mesa(1, 1, true, getEstabelecimentoMock());
    }

    private Conta getContaMock() {
        return new Conta(1, true, 100, "123", getMesaMock());
    }
}
