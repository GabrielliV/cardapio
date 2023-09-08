package com.sistema.cardapio.dto;

import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContaDto {
    private Conta conta;
    private List<Pedido> pedidos;
}
