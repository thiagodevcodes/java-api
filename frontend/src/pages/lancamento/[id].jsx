import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById, getAllData } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";
import Loading from "@/components/Loading";

export default function GrupoDespesaById() {
  const router = useRouter();
  const [lancamento, setLancamento] = useState({});
  const [acao, setAcao] = useState([]);
  const [grupoDespesa, setGrupoDespesa] = useState([]);
  const [elementoDespesa, setElementoDespesa] = useState([]);
  const [fonteRecurso, setFonteRecurso] = useState([]);
  const [modalidadeAplicacao, setModalidadeAplicacao] = useState([]);
  const [objetivoEstrategico, setObjetivoEstrategico] = useState([]);
  const [programa, setPrograma] = useState([]);
  const [solicitante, setSolicitante] = useState([]);
  const [tipoLancamento, setTipoLancamento] = useState([]);
  const [tipoTransacao, setTipoTransacao] = useState([]);
  const [unidade, setUnidade] = useState([]);
  const [unidadeOrcamentaria, setUnidadeOrcamentaria] = useState([]);
  const [lancamentoPai, setLancamentoPai] = useState([]);
  const [loading, setLoading] = useState(false);
  const id = router.query.id;

  useEffect(() => {
    if (id) {
      fetchDataById(id, "lancamento").then((response) => {
        setLancamento(response);
        setLoading(true);
      }).catch((error) => {
        console.error(error)
      })
    }
  }, [id])

  useEffect(() => {
    if (lancamento.acao) {
      fetchDataById(lancamento.acao, "acao").then((response) => {
        setAcao(response);
      })
    }

    if (lancamento.elementoDespesa) {
      fetchDataById(lancamento.elementoDespesa, "elemento-despesa").then((response) => {
        setElementoDespesa(response);
      })
    }

    if (lancamento.fonteRecurso) {
      fetchDataById(lancamento.fonteRecurso, "fonte-recurso").then((response) => {
        setFonteRecurso(response);
      })
    }

    if (lancamento.programa) {
      fetchDataById(lancamento.programa, "programa").then((response) => {
        setPrograma(response);
      })
    }

    if (lancamento.solicitante) {
      fetchDataById(lancamento.solicitante, "solicitante").then((response) => {
        setSolicitante(response);
      })
    }

    if (lancamento.modalidadeAplicacao) {
      fetchDataById(lancamento.modalidadeAplicacao, "modalidade-aplicacao").then((response) => {
        setModalidadeAplicacao(response)
      })
    }

    if (lancamento.grupoDespesa) {
      fetchDataById(lancamento.grupoDespesa, "grupo-despesa").then((response) => {
        setGrupoDespesa(response)
      })
    }

    if (lancamento.objetivoEstrategico) {
      fetchDataById(lancamento.objetivoEstrategico, "objetivo-estrategico").then((response) => {
        setObjetivoEstrategico(response)
      })
    }

    if (lancamento.tipoLancamento) {
      fetchDataById(lancamento.tipoLancamento, "tipo-lancamento").then((response) => {
        setTipoLancamento(response)
      })
    }

    if (lancamento.tipoTransacao) {
      fetchDataById(lancamento.tipoTransacao, "tipo-transacao").then((response) => {
        setTipoTransacao(response)
      })
    }

    if (lancamento.unidade) {
      fetchDataById(lancamento.unidade, "unidade").then((response) => {
        setUnidade(response)
      })
    }

    if (lancamento.unidadeOrcamentaria) {
      fetchDataById(lancamento.unidadeOrcamentaria, "unidade-orcamentaria").then((response) => {
        setUnidadeOrcamentaria(response)
      })
    }

    if (lancamento.lancamentoPai) {
      fetchDataById(lancamento.lancamentoPai, "lancamento").then((response) => {
        setLancamentoPai(response);
      })
    }
    setLoading(true);
  }, [lancamento])

  if (!lancamento) {
    return <div>Lançamento não encontrado</div>;
  }

  if (!lancamento.dataLancamento) return

  var dataOriginal = lancamento.dataLancamento;
  var partes = dataOriginal.split("-");
  var dataFormatada = partes[2] + "/" + partes[1] + "/" + partes[0];

  return (
    <Layout>
      {
        loading && 
        <div className={styles.container}>
        <h1>Detalhes do Lançamento</h1>
        <p>Id: {lancamento.id}</p>
        <p>Número Lançamento: {lancamento.numeroLancamento}</p>
        <p>Ano Orçamento: {lancamento.anoOrcamento}</p>
        <p>Contratado: {lancamento.contratado}</p>
        <p>Descrição: {lancamento.descricao}</p>
        <p>Valor: {lancamento.valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
        <p>GED: {lancamento.ged}</p>
        <p>Lançamento Inválido: {lancamento.lancamentoInvalido ? "Sim" : "Não"}</p>
        <p>Data de Lançamento: {dataFormatada}</p>
        <p>Lançamento Pai: {lancamento.lancamentoPai ? lancamentoPai.descricao : "Não possui"}</p>
        <p>Elemento Despesa: {elementoDespesa.nome ? elementoDespesa.nome : null}</p>
        <p>Fonte Recurso: {fonteRecurso.nome ? fonteRecurso.nome : null}</p>
        <p>Ação: {acao.nome ? acao.nome : null}</p>
        <p>Grupo Despesa: {grupoDespesa.nome ? grupoDespesa.nome : null}</p>
        <p>Modalidade de Aplicação: {modalidadeAplicacao.nome ? modalidadeAplicacao.nome : null}</p>
        <p>Obetivo Estratégico: {objetivoEstrategico.nome ? objetivoEstrategico.nome : null}</p>
        <p>Programa: {programa.nome ? programa.nome : null}</p>
        <p>Solicitante: {solicitante.nome ? solicitante.nome : null}</p>
        <p>Tipo Lançamento: {tipoLancamento.nome ? tipoLancamento.nome : null}</p>
        <p>Tipo de Transação: {tipoTransacao.nome ? tipoTransacao.nome : null}</p>
        <p>Unidade: {unidade.nome ? unidade.nome : null}</p>
        <p>Unidade Orçamentária: {unidadeOrcamentaria.nome ? unidadeOrcamentaria.nome : null}</p>
      </div>
      }
      
      <Loading/>
    </Layout>
  );
};



