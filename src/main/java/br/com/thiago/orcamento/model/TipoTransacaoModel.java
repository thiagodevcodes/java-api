package br.com.thiago.orcamento.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="tipo_transacao")
public class TipoTransacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @CreationTimestamp
    @Column(name = "dataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "dataAlteracao")
    private LocalDateTime dataAlteracao;
}
