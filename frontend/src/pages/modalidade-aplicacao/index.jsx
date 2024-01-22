import Table from "@/components/Table";
import Layout from "@/components/Layout";
import Pagination from "@/components/Pagination";
import Header from "@/components/Header";
import Modal from "@/components/Modal";
import ModalUpdate from "@/components/ModalUpdate";
import ModalDelete from "@/components/ModalDelete";
import { ToastContainer } from "react-toastify";
import { useState, useEffect } from "react"
import { fetchData } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import InputForm from "@/components/InputForm";
import Loading from "@/components/Loading";

export default function ModalidadeAplicacao() {
  const [model, setModel] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [modalOpen, setModalOpen] = useState({ post: false, update: false, delete: false });
  const [id, setId] = useState(null);
  const [formData, setFormData] = useState({ codigo: "", nome: "" });
  const [loading, setLoading] = useState(false)

  const columns = [
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
      update: modal === "update" ? isOpen : false,
      delete: modal === "delete" ? isOpen : false
    });
  };

  useEffect(() => {
    setLoading(false)
    fetchData(10, currentPage, "modalidade-aplicacao").then((response) => {
      setModel(response.content);
      setTotalPages(response.totalPages);
      setLoading(true)
    }).catch((error) => {
      console.error(error)
    })
  }, [currentPage]);

  useEffect(() => {
    if (modalOpen.update == false)
      setFormData({ nome: "", codigo: "" })
  }, [modalOpen.update])



  return (
    <Layout title="Orçamento Público">
      <Header controlModal={controlModal} title="Modalidades de Aplicação" img="/icons/Transfer.svg" />
      { loading && model && <Table columns={columns} model={model} controlModal={controlModal} setId={setId} title="modalidade-aplicacao" path="modalidade-aplicacao" />}

      { !loading && <Loading/> }

      {model.length == 0 ? null :
        <Pagination
          currentPage={currentPage}
          setCurrentPage={setCurrentPage}
          totalPages={totalPages}
        />
      }

      {modalOpen.post ?
        <Modal title="Adicionar" controlModal={controlModal} path={"modalidade-aplicacao"} formData={formData}>
          <InputForm
            key={"codigo"}
            id={"codigo"}
            type={"number"}
            title={"Código"}
            htmlFor={"codigo"}
            onChange={(e) => handleInputChange("codigo", e)}
          >
          </InputForm>
          <InputForm
            key={"nome"}
            id={"nome"}
            type={"text"}
            title={"Nome"}
            htmlFor={"nome"}
            onChange={(e) => handleInputChange("nome", e)}
          >
          </InputForm>
        </Modal>
        : modalOpen.update ?
          <ModalUpdate setFormData={setFormData} model={model} id={id} title="Editar" controlModal={controlModal} path={"modalidade-aplicacao"} formData={formData}>
            <InputForm
              key={"codigo"}
              id={"codigo"}
              type={"number"}
              title={"Código"}
              htmlFor={"codigo"}
              onChange={(e) => handleInputChange("codigo", e)}
              value={formData.codigo}
            >
            </InputForm>
            <InputForm
              key={"nome"}
              id={"nome"}
              type={"text"}
              title={"Nome"}
              htmlFor={"Nome"}
              onChange={(e) => handleInputChange("nome", e)}
              value={formData.nome}
            >
            </InputForm>
          </ModalUpdate>
          : null}

      {modalOpen.delete ?
        <ModalDelete path="lancamento" id={id} controlModal={controlModal} title={"Confirmar Exclusão?"}></ModalDelete>
        : null
      }
      <ToastContainer />
    </Layout>
  )
}