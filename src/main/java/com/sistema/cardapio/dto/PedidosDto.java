package com.sistema.cardapio.dto;

import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidosDto {
    private int id;
    private LocalTime hora_pedido;
    private int mesa_id;
}
