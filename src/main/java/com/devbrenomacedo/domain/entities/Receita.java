package com.devbrenomacedo.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devbrenomacedo.domain.enums.Categoria;

@Entity
@Table(name = "receita")
public class Receita extends Movimentacao {
    private static List<Receita> historico = new ArrayList<>();

    public Receita(Long id, String origem, Double valor, Categoria categoria, Boolean recorrente, Instant data) {
        super(id, origem, valor, categoria, recorrente, data);

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da receita inválido");
        }

        Movimentacao.adicionarMovimentacao(this);
        Receita.adicionarReceita(this);

    }

    public static void adicionarReceita(Receita obj) {
        Receita.historico.add(obj);
    }

    public static List<Receita> getReceitas() {
        return historico;
    }
}
