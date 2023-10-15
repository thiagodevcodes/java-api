package br.com.thiago.orcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiago.orcamento.model.ModalidadeAplicacaoModel;

public interface ModalidadeAplicacaoRepository extends JpaRepository<ModalidadeAplicacaoModel, Integer> {
    Optional<ModalidadeAplicacaoModel> findByNome(String nome);
    Optional<ModalidadeAplicacaoModel> findByCodigo(Float codigo);
}
