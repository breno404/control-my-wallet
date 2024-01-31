package com.devbrenomacedo.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbrenomacedo.application.interfaces.MovimentacaoService;
import com.devbrenomacedo.application.repositories.MovimentacaoRepository;
import com.devbrenomacedo.domain.entities.Gasto;
import com.devbrenomacedo.domain.entities.Receita;

@Service
public class GastoService implements MovimentacaoService<Gasto> {

    @Autowired
    private MovimentacaoRepository<Gasto> repository;

    public GastoService(MovimentacaoRepository<Gasto> gastoRepository) {
        this.repository = gastoRepository;
    }

    public Gasto localizar(Long id) {
        Optional<Gasto> gasto = repository.findById(id);

        return gasto.orElseThrow();
    }

    public List<Gasto> localizar() {
        return repository.findAll();
    }

    public Gasto registrar(Gasto gasto) {
        return repository.save(gasto);
    }

    public Gasto atualizar(Long id, Gasto gasto) {
        try {
            Gasto gastoDB = repository.getReferenceById(id);
            gastoDB.setOrigem(gasto.getOrigem());
            gastoDB.setValor(gasto.getValor());
            gastoDB.setCategoria(gasto.getCategoria());
            gastoDB.setData(gasto.getData());
            gastoDB.setRecorrente(gasto.getRecorrente());

            return repository.save(gastoDB);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void remover(Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw e;
        }
    }

}
