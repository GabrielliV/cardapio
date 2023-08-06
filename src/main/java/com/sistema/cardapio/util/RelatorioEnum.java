package com.sistema.cardapio.util;

public enum RelatorioEnum {
    PRATOS_MAIS_SOLICITADOS(1),
    PRATOS_MENOS_SOLICITADOS(2),
    TEMPO_MEDIO_ENTREGA(3);

    private final int value;

    RelatorioEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RelatorioEnum fromValue(int value) {
        for (RelatorioEnum enumValue : values()) {
            if (enumValue.value == value) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Nenhum enum foi encontrado com esse valor: " + value);
    }
}
