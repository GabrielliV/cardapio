package com.sistema.cardapio.dto;

import com.sistema.cardapio.model.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDto {
    private int itemId;
    private String nome;
    private int qtde;
    private double valor;
}
