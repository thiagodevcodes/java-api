import Table from "@/components/Table";
import Layout from "@/components/Layout";
import Pagination from "@/components/Pagination";
import Header from "@/components/Header";
import Modal from "@/components/Modal";
import ModalUpdate from "@/components/ModalUpdate";
import { fetchData } from "@/services/axios";
import { ToastContainer } from "react-toastify";
import { useState, useEffect } from "react"
import "react-toastify/dist/ReactToastify.css";

export default function UnidadeOrcamentaria() {
    const [model, setModel] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [modalOpen, setModalOpen] = useState({ post: false, update: false });
    const [id, setId] = useState(null);
  
    const controlModal = (modal, isOpen) => {
      setModalOpen({
        post: modal === "post" ? isOpen : false,
        update: modal === "update" ? isOpen : false
      });
    };
  
    useEffect(() => {
      fetchData(10, currentPage, "unidade-orcamentaria").then((response) => {
        setModel(response.content);
        setTotalPages(response.totalPages);
      }).catch((error) => {
        console.error(error)
      })
    }, [currentPage]);
      
    return (
      <Layout title="Orçamento Público">
        <Header controlModal={controlModal} title="Unidades Orçamentarias" img="/icons/Unit.svg"/>
        <Table columns={["id", "nome", "codigo"]} model={model} controlModal={controlModal} setId={setId} title="unidade-orcamentaria" path="unidade-orcamentaria"/>
          
        {model.length == 0 ? null : 
          <Pagination 
            currentPage={currentPage}
            setCurrentPage={setCurrentPage}
            totalPages={totalPages}
          />
        }

        {modalOpen.post && <Modal columns={["nome", "codigo"]} title="Adicionar Unidade Orçamentaria" controlModal={controlModal} path="unidade-orcamentaria" />}
        {modalOpen.update && <ModalUpdate model={model} id={id} title="Editar Unidade Orçamentaria" controlModal={controlModal} path="unidade-orcamentaria" />}
        <ToastContainer/>
      </Layout>
    )
  }