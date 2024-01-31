package com.devbrenomacedo.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devbrenomacedo.domain.enums.Categoria;

@Entity
@Table(name = "gasto")
public class Gasto extends Movimentacao {
    private static List<Gasto> historico = new ArrayList<>();

    public Gasto(Long id, String origem, Double valor, Categoria categoria, Boolean recorrente, Instant data) {
        super(id, origem, valor * -1, categoria, recorrente, data);

        if (valor * -1 > 0) {
            throw new IllegalArgumentException("Valor do gasto inválido");
        }

        Movimentacao.adicionarMovimentacao(this);
        Gasto.adicionarGasto(this);
    }

    public static void adicionarGasto(Gasto obj) {
        Gasto.historico.add(obj);
    }

    public static List<Gasto> getGastos() {
        return historico;
    }
}
