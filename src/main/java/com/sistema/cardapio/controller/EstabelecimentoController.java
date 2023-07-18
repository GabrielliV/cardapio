package com.sistema.cardapio.controller;

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

    @PostMapping("/login")
    public ResponseEntity<Estabelecimento> validaEstabelecimento(
            @RequestBody Estabelecimento estabelecimento) {
        Estabelecimento estabelecimentoValido = estabelecimentoService.validaEstabelecimento(
                estabelecimento.getLogin(), estabelecimento.getIdentificador());

        if (estabelecimentoValido != null) {
            return ResponseEntity.ok(estabelecimentoValido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
