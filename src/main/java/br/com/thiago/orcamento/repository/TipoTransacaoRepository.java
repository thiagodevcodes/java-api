package br.com.thiago.orcamento.repository;

import br.com.thiago.orcamento.model.TipoLancamentoModel;
import br.com.thiago.orcamento.model.TipoTransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoTransacaoRepository extends JpaRepository<TipoTransacaoModel, Integer> {
    Optional<TipoTransacaoModel> findByNome(String nome);
}
