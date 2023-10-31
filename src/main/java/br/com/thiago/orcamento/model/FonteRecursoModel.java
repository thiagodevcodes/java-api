package br.com.thiago.orcamento.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="fonte_recurso")
public class FonteRecursoModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;
}