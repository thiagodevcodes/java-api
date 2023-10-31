package br.com.thiago.orcamento.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="unidade_orcamentaria")
public class UnidadeOrcamentariaModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", unique = true, nullable = false)
    private Integer codigo;

    @Column(name="nome", length = 255, nullable = false)
    private String nome;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    private LocalDateTime dataAlteracao;
}
