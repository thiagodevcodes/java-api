import { useState, useEffect } from "react"
import axios from "axios";
import Table from "@/components/Table";
import Layout from "@/components/Layout";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";


export default function Acao() {
    const [acao, setAcao] = useState([]);

    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/orcamento/acao`);
        setAcao(response.data);
      } catch (error) {
        //console.error('Erro ao buscar dados:', error);
        toast.error("Erro ao buscar dados!")
      }
    };   

    useEffect(() => {
        fetchData();
    }, []);
    
    return (
        <Layout titulo="Orçamento Público">
          <Table acao={acao} title="acao"/>
          <ToastContainer/>
        </Layout>
    )
}