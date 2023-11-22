package br.com.thiago.orcamento.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.orcamento.model.GrupoDespesaModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoDespesaRepository extends JpaRepository<GrupoDespesaModel, Integer>{
    Optional<GrupoDespesaModel> findByNome(String nome);
    Optional<GrupoDespesaModel> findByCodigo(Float codigo);

    List<GrupoDespesaModel> findAllByOrderByIdAsc(Pageable pageable);
}
