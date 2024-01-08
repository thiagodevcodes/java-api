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
import InputForm from "@/components/InputForm";

export default function UnidadeOrcamentaria() {
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
      fetchData(10, currentPage, "unidade-orcamentaria").then((response) => {
        setModel(response.content);
        setTotalPages(response.totalPages);
      }).catch((error) => {
        console.error(error)
      })
    }, [currentPage]);

    useEffect(() => {
      if(modalOpen.update == false)
        setFormData({ nome: "" })
    }, [modalOpen.update])
      
    return (
      <Layout title="Orçamento Público">
        <Header controlModal={controlModal} title="Unidades Orçamentárias" img="/icons/Action.svg"/>
        <Table columns={data} model={model} controlModal={controlModal} setId={setId} title="unidade-orcamentaria" path="unidade-orcamentaria"/>
          
        {model.length == 0 ? null : 
          <Pagination 
            currentPage={currentPage}
            setCurrentPage={setCurrentPage}
            totalPages={totalPages}
          />
        }
  
        {modalOpen.post ? 
          <Modal title="Adicionar Unidade Orçamentária" controlModal={controlModal} path={"unidade-orcamentaria"} formData={ formData }>
                  <InputForm         
                      key={"codigo  "}    
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
          <ModalUpdate setFormData={setFormData} model={model} id={id} title="Editar Unidade Orçamentária" controlModal={controlModal} path={"unidade-orcamentaria"} formData={ formData }>
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