package br.com.thiago.orcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiago.orcamento.model.AcaoModel;

public interface AcaoRepository extends JpaRepository<AcaoModel, Integer> {
    Optional<AcaoModel> findByNome(String nome);
    Optional<AcaoModel> findByCodigo(Integer codigo);
}
