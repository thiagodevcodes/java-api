package br.com.thiago.orcamento.repository;

import br.com.thiago.orcamento.model.TipoLancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoLancamentoRepository extends JpaRepository<TipoLancamentoModel, Integer> {

    Optional<TipoLancamentoModel> findById(Integer id);
    Optional<TipoLancamentoModel> findByNome(String nome);
}
