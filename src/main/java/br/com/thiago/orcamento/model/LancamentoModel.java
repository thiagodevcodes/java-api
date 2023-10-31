package br.com.thiago.orcamento.model;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="lancamento")
public class LancamentoModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "numero_lancamento")
    private Integer numeroLancamento;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "lancamento_invalido", nullable = false)
    private Boolean lancamentoInvalido;

    @Column(name = "contratado", length = 255)
    private String contratado;

    @Column(name = "ged", length = 27)
    private Character ged;

    @Column(name = "ano_orcamento", nullable = false)
    private Short anoOrcamento;

    @Column(name = "valor", length = 4, nullable = false)
    private Float valor;

    @ManyToOne
    @JoinColumn(name = "id_tipo_lancamento", nullable = false)
    private TipoLancamentoModel idTipoLancamento;

    @ManyToOne
    @JoinColumn(name = "id_lancamento_pai")
    private LancamentoModel idLancamentoPai;
    
    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private UnidadeModel idUnidade;

    @ManyToOne
    @JoinColumn(name = "id_unidade_orcamentaria", nullable = false)
    private UnidadeOrcamentariaModel idUnidadeOrcamentaria;

    @ManyToOne
    @JoinColumn(name = "id_programa", nullable = false)
    private ProgramaModel idPrograma;

    @ManyToOne
    @JoinColumn(name = "id_acao", nullable = false)
    private AcaoModel idAcao;

    @ManyToOne
    @JoinColumn(name = "id_fonte_recurso", nullable = false)
    private FonteRecursoModel idFonteRecurso;

    @ManyToOne
    @JoinColumn(name = "id_grupo_despesa", nullable = false)
    private GrupoDespesaModel idGrupoDespesa;

    @ManyToOne
    @JoinColumn(name = "id_modalidade_aplicacao", nullable = false)
    private ModalidadeAplicacaoModel idModalidadeAplicacao;

    @ManyToOne
    @JoinColumn(name = "id_elemento_despesa", nullable = false)
    private ElementoDespesaModel idElementoDespesa;

    @ManyToOne
    @JoinColumn(name = "id_solicitante", referencedColumnName = "id")
    private SolicitanteModel idSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_objetivo_estrategico")
    private ObjetivoEstrategicoModel idObjetivoEstrategico;

    @ManyToOne
    @JoinColumn(name = "id_tipo_transacao", nullable = false)
    private TipoTransacaoModel idTipoTransacao;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;
}