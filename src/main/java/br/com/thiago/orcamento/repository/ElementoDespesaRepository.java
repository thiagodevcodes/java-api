package br.com.thiago.orcamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thiago.orcamento.model.ElementoDespesaModel;
import java.util.Optional;

public interface ElementoDespesaRepository extends JpaRepository<ElementoDespesaModel, Integer>{
    Optional<ElementoDespesaModel> findByNome(String nome);
    Optional<ElementoDespesaModel> findByCodigo(Integer codigo);
}
