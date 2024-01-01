import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";

export default function TipoTransacaoById() {
  const router = useRouter();
  const [tipoTransacao, setTipoTransacao] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "tipo-transacao").then((response) => {
        setTipoTransacao(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!tipoTransacao) {
      return <div>Tipo de Transação não encontrada</div>;
  }

  return (
      <Layout>
          <p>Id: {tipoTransacao.id}</p>
          <p>Nome: {tipoTransacao.nome}</p>
      </Layout>
  );
};





