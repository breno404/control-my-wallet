package com.devbrenomacedo.domain.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devbrenomacedo.domain.enums.Categoria;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public abstract class Movimentacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origem;
    private Double valor;
    private Categoria categoria;
    private Boolean recorrente;
    private Instant data;
    private static List<Movimentacao> historico = new ArrayList<>();

    protected Movimentacao(Long id, String origem, Double valor, Categoria categoria, Boolean recorrente,
            Instant data) {
        super();
        this.id = id;
        this.origem = origem;
        this.valor = valor;
        this.categoria = categoria;
        this.recorrente = recorrente;
        this.data = data;
    }

    public static void adicionarMovimentacao(Movimentacao obj) {
        historico.add(obj);
    }

    public static List<Movimentacao> getMovimentacoes() {
        return historico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getRecorrente() {
        return recorrente;
    }

    public void setRecorrente(Boolean recorrente) {
        this.recorrente = recorrente;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((origem == null) ? 0 : origem.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
        result = prime * result + ((recorrente == null) ? 0 : recorrente.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movimentacao other = (Movimentacao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (origem == null) {
            if (other.origem != null)
                return false;
        } else if (!origem.equals(other.origem))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        if (categoria != other.categoria)
            return false;
        if (recorrente == null) {
            if (other.recorrente != null)
                return false;
        } else if (!recorrente.equals(other.recorrente))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        return true;
    }

}
