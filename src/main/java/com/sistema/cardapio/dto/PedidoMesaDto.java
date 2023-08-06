package com.sistema.cardapio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoMesaDto {
    private double total;
    private String cpf;
    List<ItemPedidoDto> itensDto;
}
