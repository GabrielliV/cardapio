package com.sistema.cardapio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String descricao;
    private double preco;
    private String foto;
    private boolean ativo;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}
