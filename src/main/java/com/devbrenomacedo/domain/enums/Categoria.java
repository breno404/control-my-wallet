package com.devbrenomacedo.domain.enums;

public enum Categoria {
    FATURA(1),
    MERCADO(2),
    FINANCIAMENTO(3),
    EXTRA(4),
    SALARIO(5),
    RESTAURANTE(6),
    BAR(7),
    VIAGEM(8),
    EMPRESTIMO(9),
    DECIMO_TERCEIRO(10),
    FERIAS(11),
    DESCONTO(12),
    IRPF(13);

    private Integer codigo;

    private Categoria(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static Categoria valueOf(int codigo) {
        for (Categoria value : Categoria.values()) {
            if (value.getCodigo() == codigo) {
                return value;
            }
        }

        throw new IllegalArgumentException("Código de categoria inválido");
    }
}
