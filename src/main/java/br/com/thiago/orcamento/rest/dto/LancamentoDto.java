package br.com.thiago.orcamento.rest.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDto {
    private Integer id;
    private Boolean lancamentoInvalido;
    private Integer numeroLancamento;
    private LocalDate dataLancamento;
    private String descricao;
    private String ged;
    private String contratado;
    private Float valor;
    private Integer anoOrcamento;
    private Integer tipoLancamento;
    private Integer lancamentoPai;
    private Integer unidade;
    private Integer unidadeOrcamentaria;
    private Integer programa;
    private Integer acao;
    private Integer fonteRecurso;
    private Integer grupoDespesa;
    private Integer elementoDespesa;
    private Integer solicitante;
    private Integer objetivoEstrategico;
    private Integer tipoTransacao;
    private Integer modalidadeAplicacao;
}
