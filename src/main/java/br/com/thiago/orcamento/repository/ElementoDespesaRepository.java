package br.com.thiago.orcamento.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.thiago.orcamento.model.ElementoDespesaModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementoDespesaRepository extends JpaRepository<ElementoDespesaModel, Integer>{
    Optional<ElementoDespesaModel> findByNome(String nome);
    Optional<ElementoDespesaModel> findByCodigo(Integer codigo);

    List<ElementoDespesaModel> findAllByOrderByIdAsc(Pageable pageable);
}
