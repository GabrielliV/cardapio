package com.sistema.cardapio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private double total;
    private String CPF;
    @ManyToOne
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;

}
