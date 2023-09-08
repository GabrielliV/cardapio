package com.sistema.cardapio.controller;

import com.sistema.cardapio.dto.EstabalecimentoDto;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio/estabelecimentos")
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @Autowired
    public EstabelecimentoController(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    @GetMapping
    public List<Estabelecimento> listarUsuarios() {
        return estabelecimentoService.listarEstabelecimento();
    }

    @GetMapping("/login")
    public EstabalecimentoDto validaEstabelecimento(
            @RequestBody Estabelecimento estabelecimento) {
        return estabelecimentoService.loginEstabelecimento(
                estabelecimento.getLogin(), estabelecimento.getIdentificador());
    }
}
