package com.devbrenomacedo.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devbrenomacedo.application.interfaces.MovimentacaoService;
import com.devbrenomacedo.application.repositories.MovimentacaoRepository;
import com.devbrenomacedo.domain.entities.Receita;

@Service
public class ReceitaService implements MovimentacaoService<Receita> {

    @Autowired
    private MovimentacaoRepository<Receita> repository;

    public ReceitaService(MovimentacaoRepository<Receita> receitaRepository) {
        this.repository = receitaRepository;
    }

    public Receita localizar(Long id) {
        Optional<Receita> receita = repository.findById(id);

        return receita.orElseThrow();
    }

    public List<Receita> localizar() {
        return repository.findAll();
    }

    public Receita registrar(Receita receita) {
        return repository.save(receita);
    }

    public Receita atualizar(Long id, Receita receita) {
        try {
            Receita receitaDB = repository.getReferenceById(id);
            receitaDB.setOrigem(receita.getOrigem());
            receitaDB.setValor(receita.getValor());
            receitaDB.setCategoria(receita.getCategoria());
            receitaDB.setData(receita.getData());
            receitaDB.setRecorrente(receita.getRecorrente());

            return repository.save(receitaDB);
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
