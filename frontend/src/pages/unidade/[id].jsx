import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import styles from "../../styles/Details.module.css";

export default function UnidadeById() {
  const router = useRouter();
  const [unidade, setUnidade] = useState({});
  const id = router.query.id;

  useEffect(() => {
    if (id) {
      fetchDataById(id, "unidade").then((response) => {
        setUnidade(response);
      }).catch((error) => {
        console.error(error)
      })
    }
  }, [id])

  if (!unidade) {
    return <div>Unidade não encontrada</div>;
  }

  return (
    <Layout>
      <div className={styles.container}>
        <h1>Detalhes de Unidade</h1>
        <p>Id: {unidade.id}</p>
        <p>Nome: {unidade.nome}</p>
      </div>

    </Layout>
  );
};





