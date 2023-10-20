package br.com.thiago.orcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.orcamento.model.ModalidadeAplicacaoModel;

@Repository
public interface ModalidadeAplicacaoRepository extends JpaRepository<ModalidadeAplicacaoModel, Integer> {
    Optional<ModalidadeAplicacaoModel> findByNome(String nome);
    Optional<ModalidadeAplicacaoModel> findByCodigo(Float codigo);
}
