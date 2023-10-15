package br.com.thiago.orcamento.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "modadalidade_aplicacao")
public class ModalidadeAplicacaoModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", nullable = false)
    private Float codigo;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;
}
