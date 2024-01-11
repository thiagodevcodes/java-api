import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";

export default function FonteRecursoById() {
  const router = useRouter();
  const [fonteRecurso, setFonteRecurso] = useState({});
  const id = router.query.id;

  useEffect(() => {
    if (id) {
      fetchDataById(id, "fonte-recurso").then((response) => {
        setFonteRecurso(response);
      }).catch((error) => {
        console.error(error)
      })
    }
  }, [id])

  if (!fonteRecurso) {
    return <div>Fonte Recurso não encontrada</div>;
  }

  return (
    <Layout>
      <div className={styles.container}>
        <h1>Detalhes de Fonte Recurso</h1>
        <p>Id: {fonteRecurso.id}</p>
        <p>Nome: {fonteRecurso.nome}</p>
        <p>Código: {fonteRecurso.codigo}</p>
      </div>
    </Layout>
  );
};





