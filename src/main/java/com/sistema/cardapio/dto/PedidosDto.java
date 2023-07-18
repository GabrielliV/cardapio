package com.sistema.cardapio.dto;

import com.sistema.cardapio.model.Conta;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidosDto {
    private int id;
    private LocalTime hora_pedido;
    private int mesa_id;
}
