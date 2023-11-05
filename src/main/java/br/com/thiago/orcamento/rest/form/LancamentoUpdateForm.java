package br.com.thiago.orcamento.rest.form;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LancamentoUpdateForm {
    private Boolean lancamentoInvalido;
    private Integer numeroLancamento;
     
    @FutureOrPresent(message = "A data tem que ser de hoje em diante")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Size(max = 255, message = "A descrição pode conter no máximo 255 caracteres")
    private String descricao;

    @NotBlank(message = "O GED não pode estar em branco")
    @Size(max = 27, message = "O GED pode conter no máximo 27 caracteres")
    private String ged;
   
    @Size(max = 255, message = "O contratado por conter no máximo 255 caracteres")
    private String contratado;

    private Float valor;
    private Short anoOrcamento;
    private Integer tipoLancamento;
    private Integer lancamentoPai;
    private Integer unidade;
    private Integer unidadeOrcamentaria;
    private Integer programa;
    private Integer acao;
    private Integer fonteRecurso;
    private Integer elementoDespesa;
    private Integer solicitante;
    private Integer objetivoEstrategico;
    private Integer tipoTransacao;
    private Integer grupoDespesa;
    private Integer modalidadeAplicacao;
}
