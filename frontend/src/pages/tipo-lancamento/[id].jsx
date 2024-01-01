import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";

export default function TipoLancamentoById() {
  const router = useRouter();
  const [tipoLancamento, setTipoLancamento] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "tipo-lancamento").then((response) => {
        setTipoLancamento(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!tipoLancamento) {
      return <div>Tipo de Lançamento não encontrado</div>;
  }

  return (
      <Layout>
          <p>Id: {tipoLancamento.id}</p>
          <p>Nome: {tipoLancamento.nome}</p>
      </Layout>
  );
};





