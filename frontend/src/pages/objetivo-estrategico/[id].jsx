import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";

export default function ObjetivoEstrategicoById() {
  const router = useRouter();
  const [objetivoEstrategico, setObjetivoEstrategico] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "objetivo-estrategico").then((response) => {
        setObjetivoEstrategico(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!objetivoEstrategico) {
      return <div>Objetivo Estratégico não encontrado</div>;
  }

  return (
      <Layout>
        <div className={styles.container}>
          <h1>Detalhes de Objetivo Estratégico</h1>
          <p>Id: {objetivoEstrategico.id}</p>
          <p>Nome: {objetivoEstrategico.nome}</p>
        </div>
      </Layout>
  );
};





