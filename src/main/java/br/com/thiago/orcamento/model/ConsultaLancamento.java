package br.com.thiago.orcamento.model;

public interface ConsultaLancamento {
    //Interface criada para receber consulta dos lançamentos
    int getId();
    Boolean getLancamentoValido();
    int getNumeroLancamento();
    String getDescricao();
    String getDataLancamento();
    Integer getIdLancamentoPai();
    float getValor();
    String getDsTipoLancamento();
    String getDsUnidade();
    String getDsUnidadeOrcamentaria();
    String getDsPrograma();
    String getDsAcao();
    String getDsFonteRecurso();
    String getDsGrupoDespesa();
    String getDsModalidadeAplicacao();
    String getDsElementoDespesa();
    String getDsSolicitante();
    String getDsObjetivoEstrategico();
    String getDsTipoTransacao();
    String getGED();
    String getContratado();
    short getAnoOrcamento();
}