package br.com.thiago.orcamento.repository;

import br.com.thiago.orcamento.model.SolicitanteModel;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitanteRepository extends JpaRepository<SolicitanteModel, Integer> {
    Optional<SolicitanteModel> findByNome(String nome);

    List<SolicitanteModel> findAllByOrderByIdAsc(Pageable pageable);
}
