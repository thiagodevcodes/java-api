package br.com.thiago.orcamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thiago.orcamento.model.GrupoDespesaModel;
import java.util.Optional;

public interface GrupoDespesaRepository extends JpaRepository<GrupoDespesaModel, Integer>{
    Optional<GrupoDespesaModel> findByNome(String nome);
    Optional<GrupoDespesaModel> findByCodigo(Float codigo);
}
