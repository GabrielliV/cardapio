package com.sistema.cardapio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int qtde;
    private double total;
    @Column(name = "id_pedido")
    private int id_pedido;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

}
