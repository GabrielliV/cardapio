package com.sistema.cardapio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MesasDto {
    private int id;
    private int mesa;
    private String status;
    private String ativo;
}
