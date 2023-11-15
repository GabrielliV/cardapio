package com.sistema.cardapio.services.impl;

import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.model.Mesa;
import com.sistema.cardapio.repository.ContaRepository;
import com.sistema.cardapio.repository.MesaRepository;
import com.sistema.cardapio.repository.PedidoRepository;
import com.sistema.cardapio.service.impl.ContaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContaServiceImplTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private MesaRepository mesaRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private ContaServiceImpl contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscaContaCodTest() {
        String cod = "123";
        int mesaId = 1;
        Conta mockConta = new Conta();
        Mockito.when(contaRepository.getContaByCodAndMesa_IdAndStatus(cod, mesaId, true)).thenReturn(mockConta);

        Conta result = contaService.buscaContaCod(cod, mesaId);

        assertNotNull(result);
        Mockito.verify(contaRepository).getContaByCodAndMesa_IdAndStatus(cod, mesaId, true);
    }

    @Test
    void criarContaTest() {
        int idMesa = 1;
        String cod = "123";
        Mockito.when(mesaRepository.getMesaById(idMesa)).thenReturn(getMesa());

        Conta mockConta = new Conta();
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(mockConta);

        Conta result = contaService.criarConta(idMesa, cod);

        assertNotNull(result);
        assertEquals(getMesa(), result.getMesa());
        assertEquals(cod, result.getCod());
        assertTrue(result.isStatus());
        assertEquals(0, result.getTotal());
        Mockito.verify(mesaRepository).getMesaById(idMesa);
        Mockito.verify(contaRepository).save(Mockito.any(Conta.class));
    }

    @Test
    void atualizaTotalTest() {
        Conta mockConta = new Conta();
        mockConta.setTotal(50);

        contaService.atualizaTotal(mockConta, 30);

        assertEquals(80, mockConta.getTotal());
        Mockito.verify(contaRepository).save(mockConta);
    }

    @Test
    void finalizaMesaTest() {
        int mesaId = 1;
        Conta mockConta1 = new Conta();
        Conta mockConta2 = new Conta();
        List<Conta> mockContas = Arrays.asList(mockConta1, mockConta2);
        Mockito.when(contaRepository.getContaByMesaId(mesaId)).thenReturn(mockContas);

        contaService.finalizaMesa(mesaId);

        Mockito.verify(contaRepository, Mockito.times(2)).save(Mockito.any(Conta.class));
        assertFalse(mockConta1.isStatus());
        assertFalse(mockConta2.isStatus());
    }

    @Test
    void finalizaCodTest() {
        String cod = "123";
        Conta mockConta1 = new Conta();
        Conta mockConta2 = new Conta();
        List<Conta> mockContas = Arrays.asList(mockConta1, mockConta2);
        Mockito.when(contaRepository.getContaByCod(cod)).thenReturn(mockContas);

        contaService.finalizaCod(cod);

        Mockito.verify(contaRepository, Mockito.times(2)).save(Mockito.any(Conta.class));
        assertFalse(mockConta1.isStatus());
        assertFalse(mockConta2.isStatus());
    }

    private Mesa getMesa() {
        Mesa mesa = new Mesa();

        mesa.setId(1);
        mesa.setMesa(1);
        mesa.setEstabelecimento(getEstabelecimento());
        mesa.setAtivo(true);

        return mesa;
    }

    private Estabelecimento getEstabelecimento() {
        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setId(1);
        estabelecimento.setNome("Estabelecimento da Gabi");
        estabelecimento.setLogin("gabi");
        estabelecimento.setIdentificador(123);

        return estabelecimento;
    }
}
