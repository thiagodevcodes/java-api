package br.com.thiago.orcamento.rest.form;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LancamentoUpdateForm {
    @NotNull(message = "O lançamento inválido não pode ser nulo")
    private Boolean lancamentoInvalido;

    @NotNull(message = "O número de lançamento não pode ser nulo")
    private Integer numeroLancamento;
     
    @NotNull(message = "A data de lançamento não pode ser nulo")
    @FutureOrPresent(message = "A data só de ser de hoje em diante")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    @NotBlank(message = "A descrição não pode estar em branco")
    @NotNull(message = "A descrição não pode ser nula")
    @Size(max = 255, message = "A descrição pode conter no máximo 255 caracteres")
    @NotNull(message = "A descrição não pode ser nula")
    private String descricao;

    @Size(max = 255, message = "O GED pode conter no máximo 255 caracteres")
    private String ged;
   
    @Size(max = 255, message = "O contratado por conter no máximo 255 caracteres")
    private String contratado;

    @NotNull(message = "O valor não pode ser nulo")
    private Float valor;

    @NotNull(message = "O ano do orçamento não pode ser nulo")
    private Integer anoOrcamento;

    @NotNull(message = "O id do tipo de lançamento não pode ser nulo")
    private Integer idTipoLancamento;

    private Integer idLancamentoPai;

    @NotNull(message = "O id da unidade não pode ser nulo")
    private Integer idUnidade;

    @NotNull(message = "O id da unidade orcamentaria não pode ser nulo")
    private Integer idUnidadeOrcamentaria;

    @NotNull(message = "O id do programa não pode ser nulo")
    private Integer idPrograma;

    @NotNull(message = "O id da ação não pode ser nulo")
    private Integer idAcao;

    @NotNull(message = "O id da fonte de recurso não pode ser nulo")
    private Integer idFonteRecurso;

    @NotNull(message = "O id do elemento despesa não pode ser nulo")
    private Integer idElementoDespesa;

    private Integer idSolicitante;

    private Integer idObjetivoEstrategico;

    @NotNull(message = "O id do tipo de transação não pode ser nulo")
    private Integer idTipoTransacao;
}
