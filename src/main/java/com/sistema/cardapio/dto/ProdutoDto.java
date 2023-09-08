package com.sistema.cardapio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDto {
    private String nome;
    private String descricao;
    private double preco;
    private String foto;
    private int categoriaId;
}
