import Table from "@/components/Table";
import Layout from "@/components/Layout";
import Pagination from "@/components/Pagination";
import Header from "@/components/Header";
import Modal from "@/components/Modal";
import ModalUpdate from "@/components/ModalUpdate";
import { ToastContainer, toast } from "react-toastify";
import { useState, useEffect } from "react"
import { fetchData } from "@/services/axios";
import InputForm from "@/components/InputForm";
import "react-toastify/dist/ReactToastify.css";

export default function ElementoDespesa() {
  const [model, setModel] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [modalOpen, setModalOpen] = useState({ post: false, update: false });
  const [id, setId] = useState(null);
  const [formData, setFormData] = useState({ codigo: "", nome: "" });

  const data = [
    { name: "Id", cod: "id" },
    { name: "Código", cod: "codigo" },
    { name: "Nome", cod: "nome" },
  ]

  const handleInputChange = (column, event) => {
    setFormData({
      ...formData,
      [column]: event.target.value,
    });
  };

  const controlModal = (modal, isOpen) => {
    setModalOpen({
      post: modal === "post" ? isOpen : false,
      update: modal === "update" ? isOpen : false
    });
  };

  useEffect(() => {
    fetchData(10, currentPage, "elemento-despesa").then((response) => {
      setModel(response.content);
      setTotalPages(response.totalPages);
    }).catch((error) => {
      console.error(error)
    })
  }, [currentPage]);

  useEffect(() => {
    if(modalOpen.update == false)
      setFormData({ nome: "", codigo: "" })
  }, [modalOpen.update])
    
  return (
    <Layout title="Orçamento Público">
      <Header controlModal={controlModal} title="Elementos Despesas" img="/icons/Cost.svg"/>
      <Table columns={data} model={model} controlModal={controlModal} setId={setId} title="elemento-despesa" path="elemento-despesa"/>
        
      {model.length == 0 ? null : 
        <Pagination 
          currentPage={currentPage}
          setCurrentPage={setCurrentPage}
          totalPages={totalPages}
        />
      }

      {modalOpen.post ? 
        <Modal title="Adicionar Elemento Despesa" controlModal={controlModal} path={"elemento-despesa"} formData={ formData }>
                <InputForm         
                    key={"codigo"}    
                    id={"codigo"}
                    type={"number"}
                    title={"Código"}
                    htmlFor={"codigo"}
                    onChange={(e) => handleInputChange("codigo", e)}
                    value={ formData.codigo }
                >
                </InputForm>
                <InputForm         
                    key={"nome"}    
                    id={"nome"}
                    type={"text"}
                    title={"Nome"}
                    htmlFor={"nome"}
                    onChange={(e) => handleInputChange("nome", e)}
                    value={ formData.nome }
                >
                </InputForm>
        </Modal>
       : modalOpen.update ? 
        <ModalUpdate setFormData={setFormData} model={model} id={id} title="Editar Elemento Despesa" controlModal={controlModal} path={"elemento-despesa"} formData={ formData}>
                <InputForm         
                    key={"codigo"}    
                    id={"codigo"}
                    type={"number"}
                    title={"Código"}
                    htmlFor={"codigo"}
                    onChange={(e) => handleInputChange("codigo", e)}
                    value={ formData.codigo }
                >
                </InputForm>
                <InputForm         
                    key={"nome"}    
                    id={"nome"}
                    type={"text"}
                    title={"Nome"}
                    htmlFor={"Nome"}
                    onChange={(e) => handleInputChange("nome", e)}
                    value={ formData.nome }
                >
                </InputForm>
        </ModalUpdate>
       : null}
      <ToastContainer/>
    </Layout>
  )
}