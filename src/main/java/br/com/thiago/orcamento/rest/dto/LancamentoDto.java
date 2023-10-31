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
    private Integer idTipoLancamento;
    private Integer idLancamentoPai;
    private Integer idUnidade;
    private Integer idUnidadeOrcamentaria;
    private Integer idPrograma;
    private Integer idAcao;
    private Integer idFonteRecurso;
    private Integer idElementoDespesa;
    private Integer idSolicitante;
    private Integer idObjetivoEstrategico;
    private Integer idTipoTransacao;
}
