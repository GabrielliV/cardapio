package com.sistema.cardapio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String observacao;
    private LocalDateTime hora_pedido;
    private LocalDateTime hora_entrega;
    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

}
