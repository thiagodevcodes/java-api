package br.com.thiago.orcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiago.orcamento.model.LancamentoModel;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer> {
    Optional<LancamentoModel> findByNumeroLancamento(Integer numeroLancamento);
}

    

    
