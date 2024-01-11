import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";

export default function GrupoDespesaById() {
  const router = useRouter();
  const [grupoDespesa, setGrupoDespesa] = useState({});
  const id = router.query.id;

  useEffect(() => {
    if (id) {
      fetchDataById(id, "grupo-despesa").then((response) => {
        setGrupoDespesa(response);
      }).catch((error) => {
        console.error(error)
      })
    }
  }, [id])

  if (!grupoDespesa) {
    return <div>Grupo Despesa não encontrada</div>;
  }

  return (
    <Layout>
      <div className={styles.container}>
        <h1>Detalhes de Grupo Despesa</h1>
        <p>Id: {grupoDespesa.id}</p>
        <p>Nome: {grupoDespesa.nome}</p>
        <p>Código: {grupoDespesa.codigo}</p>
      </div>
    </Layout>
  );
};





