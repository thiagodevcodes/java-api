import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";

export default function UnidadeOrcamentariaById() {
  const router = useRouter();
  const [unidadeOrcamentaria, setUnidadeOrcamentaria] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "unidade-orcamentaria").then((response) => {
        setUnidadeOrcamentaria(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!unidadeOrcamentaria) {
      return <div>Unidade Orçamentaria não encontrada</div>;
  }

  return (
      <Layout>
          <p>Id: {unidadeOrcamentaria.id}</p>
          <p>Nome: {unidadeOrcamentaria.nome}</p>
          <p>Código: {unidadeOrcamentaria.codigo}</p>
      </Layout>
  );
};





