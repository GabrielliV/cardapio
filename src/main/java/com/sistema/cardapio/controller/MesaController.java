package com.sistema.cardapio.controller;

import com.sistema.cardapio.dto.MesasDto;
import com.sistema.cardapio.model.Estabelecimento;
import com.sistema.cardapio.model.Mesa;
import com.sistema.cardapio.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio/mesas")
public class MesaController {
    private final MesaService mesaService;

    @Autowired
    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/listar/{estabelecimentoId}")
    public List<MesasDto> listaMesas(@PathVariable int estabelecimentoId) {
        return mesaService.mesas(estabelecimentoId);
    }

    @PostMapping("/login/{mesa}")
    public Mesa loginMesa(@RequestBody Estabelecimento estabelecimento, @PathVariable int mesa) {
        return mesaService.validaMesa(
                estabelecimento.getLogin(), estabelecimento.getIdentificador(), mesa);
    }

    @PostMapping("/criar/{estabelecimentoId}/{mesa}")
    public void criaMesa(@PathVariable int estabelecimentoId, @PathVariable int mesa) {
        mesaService.criaMesa(estabelecimentoId, mesa);
    }
}
