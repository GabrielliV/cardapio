package com.sistema.cardapio.services.impl;

import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.model.Mesa;
import com.sistema.cardapio.repository.ContaRepository;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.service.impl.ContaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContaServiceImplTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private MesaRepository mesaRepository;

    @InjectMocks
    private ContaServiceImpl contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscaContaCodTest() {
        Conta mockConta = getContaMock();
        Mockito.when(contaRepository.getContaByCodAndMesa_IdAndStatus(mockConta.getCod(), mockConta.getMesa().getId(), true))
                .thenReturn(mockConta);

        Conta result = contaService.buscaContaCod(mockConta.getCod(), mockConta.getMesa().getId());

        assertNotNull(result);
        Mockito.verify(contaRepository).getContaByCodAndMesa_IdAndStatus(mockConta.getCod(), mockConta.getMesa().getId(), true);
    }

    @Test
    void criarContaTest() {
        int idMesa = 1;
        Mockito.when(mesaRepository.getMesaById(idMesa)).thenReturn(getMesaMock());

        Conta mockConta = getContaMock();

        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(mockConta);

        Conta result = contaService.criarConta(mockConta.getMesa().getId(), mockConta.getCod());

        assertNotNull(result);
        assertEquals(mockConta.getMesa(), result.getMesa());
        assertEquals(mockConta.getCod(), result.getCod());
        assertTrue(result.isStatus());
        assertEquals(mockConta.getTotal(), result.getTotal());
        Mockito.verify(mesaRepository).getMesaById(idMesa);
        Mockito.verify(contaRepository).save(Mockito.any(Conta.class));
    }

    @Test
    void atualizaTotalTest() {
        Conta mockConta = getContaMock();

        contaService.atualizaTotal(mockConta, 30);

        assertEquals(30, mockConta.getTotal());
        Mockito.verify(contaRepository).save(mockConta);
    }

    @Test
    void finalizaMesaTest() {
        int mesaId = 1;
        List<Conta> mockContas = List.of(getContaMock());

        Mockito.when(contaRepository.getContaByMesaId(mesaId)).thenReturn(mockContas);

        contaService.finalizaMesa(mesaId);

        Mockito.verify(contaRepository, Mockito.times(1)).save(Mockito.any(Conta.class));
        assertFalse(mockContas.get(0).isStatus());
    }

    @Test
    void finalizaCodTest() {
        String cod = "123";
        List<Conta> mockContas = List.of(getContaMock());
        Mockito.when(contaRepository.getContaByCod(cod)).thenReturn(mockContas);

        contaService.finalizaCod(cod);

        Mockito.verify(contaRepository, Mockito.times(1)).save(Mockito.any(Conta.class));
        assertFalse(mockContas.get(0).isStatus());
    }

    private Conta getContaMock() {
        Conta conta = new Conta();

        conta.setId(1);
        conta.setStatus(true);
        conta.setCod("123");
        conta.setTotal(0);
        conta.setMesa(getMesaMock());

        return conta;
    }

    private Mesa getMesaMock() {
        Mesa mesa = new Mesa();

        mesa.setId(1);
        mesa.setMesa(1);
        mesa.setEstabelecimento(new Estabelecimento(1, "Estabelecimento da Gabi", "gabi", 123));
        mesa.setAtivo(true);

        return mesa;
    }

}
