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
    private String descricao;
    private LocalDate dataLancamento;
    private Integer lancamentoPai;
    private Float valor;
    private String tipoLancamento;
    private String unidade;
    private String unidadeOrcamentaria;
    private String programa;
    private String acao;
    private String fonteRecurso;
    private String grupoDespesa;
    private String modalidadeAplicacao;
    private String elementoDespesa;
    private String solicitante;
    private String objetivoEstrategico;
    private String tipoTransacao;
    private String GED;
    private String contratado;
    private Short anoOrcamento;
}
