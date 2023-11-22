package br.com.thiago.orcamento.repository;

import br.com.thiago.orcamento.model.ObjetivoEstrategicoModel;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategicoModel, Integer> {
    Optional<ObjetivoEstrategicoModel> findByNome(String nome);
 
    List<ObjetivoEstrategicoModel> findAllByOrderByIdAsc(Pageable pageable);
}
