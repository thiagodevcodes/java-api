package br.com.thiago.orcamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.thiago.orcamento.model.ConsultaLancamento;
import br.com.thiago.orcamento.model.LancamentoModel;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer> {
    Optional<LancamentoModel> findByNumeroLancamento(Integer numeroLancamento);

    @Query(value = "SELECT * FROM VW_LANCAMENTOS", nativeQuery = true)
    List<ConsultaLancamento> findLancamentos();

    @Query(value = "SELECT * FROM VW_LANCAMENTOS WHERE id = :id", nativeQuery = true)
    Optional<ConsultaLancamento> findLancamentosById(@Param("id") int id);

    List<LancamentoModel> findAllByOrderByIdAsc(Pageable pageable);
}

    

    
