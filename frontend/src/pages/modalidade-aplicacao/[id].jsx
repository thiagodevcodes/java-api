import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";

export default function ModalidadeAplicacaoById() {
  const router = useRouter();
  const [modalidadeAplicacao, setModalidadeAplicacao] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "modalidade-aplicacao").then((response) => {
        setModalidadeAplicacao(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!modalidadeAplicacao) {
      return <div>Modalidade Aplicação não encontrada</div>;
  }

  return (
      <Layout>
          <p>Id: {modalidadeAplicacao.id}</p>
          <p>Nome: {modalidadeAplicacao.nome}</p>
          <p>Código: {modalidadeAplicacao.codigo}</p>
      </Layout>
  );
};





