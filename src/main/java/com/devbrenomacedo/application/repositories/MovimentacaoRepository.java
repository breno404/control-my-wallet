package com.devbrenomacedo.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devbrenomacedo.domain.entities.Movimentacao;

public interface MovimentacaoRepository<T extends Movimentacao> extends JpaRepository<T, Long> {

}
