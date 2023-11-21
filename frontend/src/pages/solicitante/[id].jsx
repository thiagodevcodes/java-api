import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";

export default function SolicitanteById() {
  const router = useRouter();
  const [solicitante, setSolicitante] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "solicitante").then((response) => {
        setSolicitante(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!solicitante) {
      return <div>Ação não encontrada</div>;
  }

  return (
      <Layout>
          <p>Id: {solicitante.id}</p>
          <p>Nome: {solicitante.nome}</p>
          <p>Email: {solicitante.codigo}</p>
      </Layout>
  );
};





