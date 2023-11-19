package br.com.thiago.orcamento.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.orcamento.model.AcaoModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcaoRepository extends JpaRepository<AcaoModel, Integer>{
    Optional<AcaoModel> findByNome(String nome);
    Optional<AcaoModel> findByCodigo(Integer codigo);

    List<AcaoModel> findAllByOrderByIdAsc(Pageable pageable);
}
