import { useState, useEffect } from "react"
import axios from "axios";
import Table from "@/components/Table";
import Layout from "@/components/Layout";
import Pagination from "@/components/Pagination";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const LIMIT = 10;

export default function Acao() {
    const [acao, setAcao] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/orcamento/acao?size=${LIMIT}&page=${currentPage}`);
        //console.log(response.data)
        setAcao(response.data.content);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        //console.error('Erro ao buscar dados:', error);
        toast.error("Erro ao buscar dados!")
      }
    };   

    useEffect(() => {
        fetchData();
    }, [currentPage]);
    
    return (
        <Layout title="Orçamento Público">
          <Table acao={acao} title="acao"/>
          <Pagination 
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                totalPages={totalPages}
            />
            <ToastContainer/>
        </Layout>
    )
}