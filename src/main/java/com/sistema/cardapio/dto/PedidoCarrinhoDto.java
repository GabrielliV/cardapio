package com.sistema.cardapio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoCarrinhoDto {
    int estabelecimento;
    int mesa;
    String cod;
    String observacao;
}
