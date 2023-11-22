package br.com.thiago.orcamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiago.orcamento.model.FonteRecursoModel;

public interface FonteRecursoRepository extends JpaRepository<FonteRecursoModel, Integer> {
    Optional<FonteRecursoModel> findByNome(String nome);
    Optional<FonteRecursoModel> findByCodigo(Integer codigo);

    List<FonteRecursoModel> findAllByOrderByIdAsc(Pageable pageable);
}
