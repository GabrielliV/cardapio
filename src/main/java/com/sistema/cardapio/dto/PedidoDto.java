package com.sistema.cardapio.dto;

import com.sistema.cardapio.model.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {

    private int id;
    private int mesa;
    private String observacao;
    private LocalDateTime hora_pedido;
    private LocalDateTime hora_entrega;
    private List<ItemPedido> itemPedido;
}
