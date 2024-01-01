import Table from "@/components/Table";
import Layout from "@/components/Layout";
import Pagination from "@/components/Pagination";
import Header from "@/components/Header";
import Modal from "@/components/Modal";
import ModalUpdate from "@/components/ModalUpdate";
import { ToastContainer, toast } from "react-toastify";
import { useState, useEffect } from "react"
import { fetchData } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";

export default function TipoTransacao() {
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
    fetchData(10, currentPage, "tipo-transacao").then((response) => {
      setModel(response.content);
      setTotalPages(response.totalPages);
    }).catch((error) => {
      console.error(error)
    })
  }, [currentPage]);
  
    
  return (
    <Layout title="Orçamento Público">
      <Header controlModal={controlModal} title="Tipos de Transação" img="/icons/Action.svg"/>
      <Table columns={["id", "nome"]} model={model} controlModal={controlModal} setId={setId} title="tipo-transacao" path="tipo-transacao"/>
        
      {model.length == 0 ? null : 
        <Pagination 
          currentPage={currentPage}
          setCurrentPage={setCurrentPage}
          totalPages={totalPages}
        />
      }

      {modalOpen.post && <Modal columns={["nome"]} model={model} title="Adicionar Tipo Transação" controlModal={controlModal} path={"tipo-transacao"} />}
      {modalOpen.update && <ModalUpdate model={model} id={id} title="Editar Tipo Transação" controlModal={controlModal} path={"tipo-transacao"} />}
      <ToastContainer/>
    </Layout>
  )
}