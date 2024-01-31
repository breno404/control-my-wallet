package com.devbrenomacedo.application.interfaces;

import java.util.List;

import com.devbrenomacedo.domain.entities.Gasto;
import com.devbrenomacedo.domain.entities.Movimentacao;

public interface MovimentacaoService<T extends Movimentacao> {

    public T localizar(Long id);

    public List<T> localizar();

    public T registrar(T movimentacao);

    public T atualizar(Long id, T movimentacao);

    public void remover(Long id);
}
