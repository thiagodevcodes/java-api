package br.com.thiago.orcamento.model;

import java.time.LocalDate;

public interface LancamentoConsulta {
    //Interface criada para receber consulta dos lançamentos
    Integer getId();
    Boolean getLancamentoInvalido();
    Integer getNumeroLancamento();
    String getDescricao();
    LocalDate getDataLancamento();
    Integer getLancamentoPai();
    Float getValor();
    String getTipoLancamento();
    String getUnidade();
    String getUnidadeOrcamentaria();
    String getPrograma();
    String getAcao();
    String getFonteRecurso();
    String getGrupoDespesa();
    String getModalidadeAplicacao();
    String getElementoDespesa();
    String getSolicitante();
    String getObjetivoEstrategico();
    String getTipoTransacao();
    String getGED();
    String getContratado();
    Short getAnoOrcamento();
}