package com.sistema.cardapio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoCarrinhoDto {
    int mesaId;
    String cod;
    String observacao;
    List<PedidoItemDto> pedidoItem;
}
