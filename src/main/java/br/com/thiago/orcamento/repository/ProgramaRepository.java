package br.com.thiago.orcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.orcamento.model.ProgramaModel;

@Repository
public interface ProgramaRepository extends JpaRepository<ProgramaModel, Integer> {
    Optional<ProgramaModel> findByNome(String nome);
    Optional<ProgramaModel> findByCodigo(Integer codigo);
}
