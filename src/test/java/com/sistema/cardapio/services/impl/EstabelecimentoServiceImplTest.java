package com.sistema.cardapio.services.impl;

import com.sistema.cardapio.dto.EstabalecimentoDto;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.repository.EstabelecimentoRepository;
import com.sistema.cardapio.service.impl.EstabelecimentoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EstabelecimentoServiceImplTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private EstabelecimentoServiceImpl estabelecimentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarEstabelecimentoTest() {
        Mockito.when(estabelecimentoRepository.findAll()).thenReturn(getEstabelecimentosMock());

        List<Estabelecimento> result = estabelecimentoService.listarEstabelecimento();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Estabelecimento1", result.get(0).getNome());
        assertEquals("Estabelecimento2", result.get(1).getNome());
        Mockito.verify(estabelecimentoRepository).findAll();
    }

    @Test
    void loginEstabelecimentoTest() {
        String login = "login1";
        int identificador = 123;
        Estabelecimento mockEstabelecimento = getEstabelecimentoMock();
        Mockito.when(estabelecimentoRepository.getEstabelecimentoByLoginAndIdentificador(login, identificador)).thenReturn(mockEstabelecimento);

        EstabalecimentoDto result = estabelecimentoService.loginEstabelecimento(login, identificador);

        assertNotNull(result);
        assertEquals(mockEstabelecimento.getId(), result.getId());
        assertEquals(mockEstabelecimento.getNome(), result.getNome());
        Mockito.verify(estabelecimentoRepository).getEstabelecimentoByLoginAndIdentificador(login, identificador);
    }

    private List<Estabelecimento> getEstabelecimentosMock() {
        return Arrays.asList(
                new Estabelecimento(1, "Estabelecimento1", "login1", 123),
                new Estabelecimento(2, "Estabelecimento2", "login2", 456)
        );
    }

    private Estabelecimento getEstabelecimentoMock() {
        return new Estabelecimento(1, "Estabelecimento1", "login1", 123);
    }
}
