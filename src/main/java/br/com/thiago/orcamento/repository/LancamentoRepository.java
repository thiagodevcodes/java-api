package br.com.thiago.orcamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.thiago.orcamento.model.LancamentoConsulta;
import br.com.thiago.orcamento.model.LancamentoModel;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer> {
    Optional<LancamentoModel> findByNumeroLancamento(Integer numeroLancamento);
    
    List<LancamentoModel> findAllByOrderByIdAsc(Pageable pageable);

    @Query(value = "SELECT * FROM VW_LANCAMENTOS", nativeQuery = true)
    Page<LancamentoConsulta> findLancamentos(Pageable pageable);

    @Query(value = "SELECT * FROM VW_LANCAMENTOS WHERE id = :id", nativeQuery = true)
    Optional<LancamentoConsulta> findLancamentoById(@Param("id") int id);
}

    

    
