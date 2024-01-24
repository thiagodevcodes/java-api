import Layout from "@/components/Layout/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";

export default function UnidadeOrcamentariaById() {
  const router = useRouter();
  const [unidadeOrcamentaria, setUnidadeOrcamentaria] = useState({});
  const id = router.query.id;

  useEffect(() => {
    if (id) {
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
      <div className={styles.container}>
        <h1>Detalhes de Unidade Orçamentária</h1>
        <p>Id: {unidadeOrcamentaria.id}</p>
        <p>Nome: {unidadeOrcamentaria.nome}</p>
        <p>Código: {unidadeOrcamentaria.codigo}</p>
      </div>

    </Layout>
  );
};





