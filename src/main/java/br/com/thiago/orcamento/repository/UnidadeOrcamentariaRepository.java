package br.com.thiago.orcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.orcamento.model.UnidadeOrcamentariaModel;

@Repository
public interface UnidadeOrcamentariaRepository extends JpaRepository<UnidadeOrcamentariaModel, Integer>{
    Optional<UnidadeOrcamentariaModel> findByNome(String nome);
    Optional<UnidadeOrcamentariaModel> findByCodigo(Integer codigo);    
}
