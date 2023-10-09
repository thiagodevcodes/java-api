package br.com.thiago.orcamento.repository;

import br.com.thiago.orcamento.model.ObjetivoEstrategicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategicoModel, Integer> {
    Optional<ObjetivoEstrategicoModel> findByNome(String nome);
}
