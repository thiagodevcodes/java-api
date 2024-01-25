import Layout from "@/components/Layout/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";
import Loading from "@/components/Loading/Loading";

export default function LancamentoById() {
  const router = useRouter();
  const [lancamento, setLancamento] = useState(null);
  const [loading, setLoading] = useState(false);
  const id = router.query.id;

  useEffect(() => {
    setLoading(false)
    if (id) {
      fetchDataById(id, "lancamento").then((response) => {
        setLancamento(response);
        setLoading(true)
      }).catch((error) => {
        console.error(error)
      })
    }
  }, [id])

  if (lancamento?.dataLancamento) {
    var dataOriginal = lancamento.dataLancamento;
    var partes = dataOriginal.split("-");
    var dataFormatada = partes[2] + "/" + partes[1] + "/" + partes[0];
    
  }

  return (
    <Layout>
        <div className={styles.container}>
          <h1>Detalhes do Lançamento</h1>
          { lancamento && loading &&
            <div>
              <p>Id: {lancamento.id}</p>
              <p>Número Lançamento: {lancamento.numeroLancamento}</p>
              <p>Ano Orçamento: {lancamento.anoOrcamento}</p>
              <p>Contratado: {lancamento.contratado}</p>
              <p>Descrição: {lancamento.descricao}</p>
              <p>Valor: {lancamento.valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
              <p>GED: {lancamento.ged}</p>
              <p>Lançamento Inválido: {lancamento.lancamentoInvalido ? "Sim" : "Não"}</p>
              <p>Data de Lançamento: {dataFormatada}</p>
              <p>Lançamento Pai: {lancamento.lancamentoPai ? lancamento.lancamentoPai : "Não possui"}</p>
              <p>Elemento Despesa: {lancamento.elementoDespesa}</p>
              <p>Fonte Recurso: {lancamento.fonteRecurso }</p>
              <p>Ação: {lancamento.acao}</p>
              <p>Grupo Despesa: {lancamento.grupoDespesa}</p>
              <p>Modalidade de Aplicação: {lancamento.modalidadeAplicacao}</p>
              <p>Obetivo Estratégico: {lancamento.objetivoEstrategico}</p>
              <p>Programa: {lancamento.programa}</p>
              <p>Solicitante: {lancamento.solicitante}</p>
              <p>Tipo Lançamento: {lancamento.tipoLancamento}</p>
              <p>Tipo de Transação: {lancamento.tipoTransacao}</p>
              <p>Unidade: {lancamento.unidade}</p>
              <p>Unidade Orçamentária: {lancamento.unidadeOrcamentaria}</p>
            </div>
          }
        </div>
        { !loading && <Loading />}
    </Layout>
  );
};



