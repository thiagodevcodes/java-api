import Layout from "@/components/Layout";
import axios from "axios";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function AcaoById() {
  const router = useRouter();
  const [acao, setAcao] = useState({});
  const id = router.query.id;

  const fetchDataById = async (id) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/orcamento/acao/${id}`);
      setAcao(response.data);
    } catch (error) {
      //console.error('Erro ao buscar dados da ação:', error);
      toast.error("Erro ao buscar uma ação!")
      setAcao(null);
    }
  };

  useEffect( () => {
    if(router.query.id) {
      fetchDataById(id)
    }
  }, [router.query.id])

  if (!acao) {
      return <div>Ação não encontrada</div>;
  }

  return (
      <Layout>
          <p>Id: {acao.id}</p>
          <p>Nome: {acao.nome}</p>
          <p>Email: {acao.codigo}</p>
          <ToastContainer/>
      </Layout>
  );
};





