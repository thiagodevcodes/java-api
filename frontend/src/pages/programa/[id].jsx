import Layout from "@/components/Layout";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { fetchDataById } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";

export default function ProgramaById() {
  const router = useRouter();
  const [programa, setPrograma] = useState({});
  const id = router.query.id;

  useEffect( () => {
    if(id) {
      fetchDataById(id, "programa").then((response) => {
        setPrograma(response);
    }).catch((error) => {
        console.error(error)
    })
    }
  }, [id])

  if (!programa) {
      return <div>Programa não encontrado</div>;
  }

  return (
      <Layout>
          <p>Id: {programa.id}</p>
          <p>Nome: {programa.nome}</p>
          <p>Código: {programa.codigo}</p>
      </Layout>
  );
};





