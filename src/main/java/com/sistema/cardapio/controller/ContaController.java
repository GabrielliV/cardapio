package com.sistema.cardapio.controller;

import com.sistema.cardapio.service.ContaService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cardapio/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/finalizar/mesa/{mesaId}")
    public void finalizaMesa(@PathVariable int mesaId) {
        contaService.finalizaMesa(mesaId);
    }

    @PostMapping("/finalizar/cod/{cod}")
    public void finalizaCod(@PathVariable String cod) {
        contaService.finalizaCod(cod);
    }

}
