import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";

export default function ElementoDespesaById() {
  const router = useRouter();
  const [elementoDespesa, setElementoDespesa] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "elemento-despesa").then((response) => {
        setElementoDespesa(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!elementoDespesa) {
      return <div>Elemento Despesa não encontrado</div>;
  }

  return (
      <Layout>
          <div className={styles.container}>
            <h1>Detalhes de Elemento Desepsa</h1>
            <p>Id: {elementoDespesa.id}</p>
            <p>Nome: {elementoDespesa.nome}</p>
            <p>Código: {elementoDespesa.codigo}</p>
          </div>
      </Layout>
  );
};





