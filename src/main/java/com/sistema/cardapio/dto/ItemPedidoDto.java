package com.sistema.cardapio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDto {
    private int itemId;
    private String nome;
    private int qtde;
    private double valor;
}
