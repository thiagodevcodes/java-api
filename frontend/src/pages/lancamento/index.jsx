import Table from "@/components/Table";
import Layout from "@/components/Layout";
import Pagination from "@/components/Pagination";
import Header from "@/components/Header";
import ModalLancamento from "@/components/ModalLancamento";
import ModalLancamentoUpdate from "@/components/ModalLancamentoUpdate";
import { ToastContainer, toast } from "react-toastify";
import { useState, useEffect } from "react"
import { fetchData, getAllData } from "@/services/axios";
import "react-toastify/dist/ReactToastify.css";
import InputForm from "@/components/InputForm";
import styles from "../../styles/Modal.module.css";
import Select from "@/components/Select";
import Checkbox from "@/components/Checkbox";

export default function GrupoDespesa() {
  const [model, setModel] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [data, setData] = useState({})
  const [totalPages, setTotalPages] = useState(0);
  const [modalOpen, setModalOpen] = useState({ post: false, update: false });
  const [id, setId] = useState(null);
  const [formData, setFormData] = useState({ 
    acao: "",
    elementoDespesa: "",
    grupoDespesa: "",
    solicitante: "",
    programa: "",
    modalidadeAplicacao: "",
    tipoLancamento: "",
    tipoTransacao: "",
    fonteRecurso: "",
    objetivoEstrategico: "",
    unidadeOrcamentaria: "",
    unidade: "",
    valor: "",
    ged: "",
    contratado: "",
    lancamentoInvalido: false,
    dataLancamento: "",
    lancamentoPai: "",
    numeroLancamento: ""
  });

  const datas = [
    { name: "Id", cod: "id" },
    { name: "Número", cod: "numeroLancamento" },
    { name: "Descrição", cod: "descricao" },
    { name: "Ged", cod: "ged"},
    { name: "Contratado", cod: "contratado" },
    { name: "Valor", cod: "valor"},
    { name: "Ano", cod: "anoOrcamento" },
    { name: "Tipo Lançamento", cod: "tipoLancamento" },
    { name: "Data Lançamento", cod: "dataLancamento" }
  ]
  
  const date = new Date()

  const ano = date.getFullYear();
  const mes = String(date.getMonth() + 1).padStart(2, '0'); // Adicionando 1 ao mês porque os meses começam do zero
  const dia = String(date.getDate()).padStart(2, '0');

  const dataFormatada = `${ano}-${mes}-${dia}`;

  const lancamento = (model.find((item => item.id === id)))

  //["id", "inválido", "número", "descrição", "ged", "contratado", "valor", "ano", "tipo",  "Data Lançamento"]

  const controlModal = (modal, isOpen) => {
    setModalOpen({
      post: modal === "post" ? isOpen : false,
      update: modal === "update" ? isOpen : false
    });
  };

  const handleSelectChange = (nameObject, e) => {
    // Atualiza o estado com os valores selecionados
    setFormData((prevValues) => ({
      ...prevValues,
      [nameObject]: e.target.value,
    }));
};

const handleCheckboxChange = (nameObject, novoValor) => {
  // Atualiza o estado ou realiza outras ações necessárias no componente pai
  setFormData((prevValues) => ({
      ...prevValues,
      [nameObject]: novoValor,
  }));

  // Obtém o valor atual do checkbox
  const valorCheckbox = novoValor;
  console.log("Valor do checkbox:", valorCheckbox);
};

const handleInputChange = (nameObject, e) => {
  // Atualiza o estado com os valores dos inputs
  setFormData((prevValues) => ({
    ...prevValues,
    [nameObject]: e.target.value,
  }));
};

  useEffect(() => {
    fetchData(10, currentPage, "lancamento").then((response) => {
      setModel(response.content);
      setTotalPages(response.totalPages);
    }).catch((error) => {
      console.error(error)
    })
  }, [currentPage]);

  useEffect(() => {
    if(modalOpen.update == false)
      setFormData({    
      acao: "",
      elementoDespesa: "",
      grupoDespesa: "",
      solicitante: "",
      programa: "",
      modalidadeAplicacao: "",
      tipoLancamento: "",
      tipoTransacao: "",
      fonteRecurso: "",
      objetivoEstrategico: "",
      unidadeOrcamentaria: "",
      unidade: "",
      valor: "",
      ged: "",
      contratado: "",
      lancamentoInvalido: false,
      dataLancamento: "",
      lancamentoPai: "", })
  }, [modalOpen.update])

  useEffect(() => {
    getAllData().then((response) => {
        setData(response)
    })        
  },[])

  console.log(formData)
  return (
    <Layout title="Orçamento Público">
      <Header controlModal={controlModal} title="Lançamentos" img="/icons/Action.svg"/>
      <Table columns={datas} model={model} controlModal={controlModal} setId={setId} title="lancamento" path="lancamento"/>
        
      {model.length == 0 ? null : 
        <Pagination 
          currentPage={currentPage}
          setCurrentPage={setCurrentPage}
          totalPages={totalPages}
        />
      }

      {modalOpen.post ? 
        <ModalLancamento title="Adicionar Lançamento" controlModal={controlModal} path={"lancamento"} formData={ formData }>
                <div className={styles.containerSelect}>
                    <Select model={data[0]} title={"Ação *"} onChange={(e) => handleSelectChange("acao", e)} nameObject={"acao"}/>
                    <Select model={data[1]} title={"Elemento Despesa *"} onChange={(e) => handleSelectChange("elementoDespesa", e)} nameObject={"elementoDespesa"}/>
                    <Select model={data[2]} title={"Programa *"} onChange={(e) => handleSelectChange("programa", e)} nameObject={"programa"}/>
                </div>

                <div className={styles.containerSelect}>
                    <Select model={data[3]} title={"Unidade *"} onChange={(e) => handleSelectChange("unidade", e)} nameObject={"unidade"}/>
                    <Select model={data[4]} title={"Grupo Despesa *"} onChange={(e) => handleSelectChange("grupoDespesa", e)} nameObject={"grupoDespesa"}/>
                    <Select model={data[5]} title={"Unidade Orçamentária *"} onChange={(e) => handleSelectChange("unidadeOrcamentaria", e)} nameObject={"unidadeOrcamentaria"}/>
                </div>

                <div className={styles.containerSelect}>
                    <Select model={data[6]} title={"Solicitante"} onChange={(e) => handleSelectChange("solicitante", e)} nameObject={"solicitante"}/>
                    <Select model={data[7]} title={"Tipo de Lançamento *"} onChange={(e) => handleSelectChange("tipoLancamento", e)} nameObject={"tipoLancamento"}/>
                    <Select model={data[8]} title={"Tipo de Transação *"} onChange={(e) => handleSelectChange("tipoTransacao", e)} nameObject={"tipoTransacao"}/>
                </div>

                <div className={styles.containerSelect}>
                    <Select model={data[9]} title={"Objetivo Estratégico"} onChange={(e) => handleSelectChange("objetivoEstrategico", e)} nameObject={"objetivoEstrategico"}/>
                    <Select model={data[10]} title={"Fonte de Recurso *"} onChange={(e) => handleSelectChange("fonteRecurso", e)} nameObject={"fonteRecurso"}/>
                    <Select model={data[11]} title={"Modalidade de Aplicação *"} onChange={(e) => handleSelectChange("modalidadeAplicacao", e)} nameObject={"modalidadeAplicacao"}/>
                </div>

                <div className={styles.containerSelect}>
                    <InputForm title={"Número Lançamento *"} type="number" onChange={(e) => handleInputChange("numeroLancamento", e)} nameObject="numeroLancamento"/>
                    <InputForm title={"Ano Orçamento *"} type="number" onChange={(e) => handleInputChange("anoOrcamento", e)} nameObject="anoOrcamento"/>
                    <InputForm title={"Contratado *"} type="text" onChange={(e) => handleInputChange("contratado", e)} nameObject="contratado"/>
                </div>

                <div className={styles.containerSelect}>
                    <Checkbox title={"Lançamento Inválido *"} onChange={handleCheckboxChange} nameObject="lancamentoInvalido"/>
                    <Select model={data[12]} title={"Lançamento Pai"} onChange={(e) => handleSelectChange("lancamentoPai", e)} nameObject={"lancamentoPai"} value={formData.lancamentoPai}/>
                    <InputForm title={"Descrição *"} type="text" onChange={(e) => handleInputChange("descricao", e)} nameObject="descricao"/>
                </div>

                <div className={styles.containerSelect}>
                    <InputForm title={"Valor *"} type="number" onChange={ (e) => handleInputChange("valor", e)} nameObject="valor"/>
                    <InputForm title={"GED *"} type="text" onChange={(e) => handleInputChange("ged", e)} nameObject="ged"/>
                    <InputForm title={"Data Lançamento *"} type="date" onChange={(e) => handleInputChange("dataLancamento", e)} nameObject="dataLancamento" min={dataFormatada}/>
                </div>
        </ModalLancamento>
       : modalOpen.update ? 
        <ModalLancamentoUpdate setFormData={setFormData} model={model} id={id} title="Editar Lançamento" controlModal={controlModal} path={"lancamento"} formData={formData}>
                <div className={styles.containerSelect}>
                    <Select defaultValue={formData.acao} id={id} model={data[0]} title={"Ação *"} onChange={(e) => handleSelectChange("acao", e)} nameObject={"acao"}/>
                    <Select model={data[1]} title={"Elemento Despesa *"} onChange={handleInputChange} nameObject={"elementoDespesa"}/>
                    <Select defaultValue={lancamento.programa} model={data[2]} title={"Programa *"} onChange={handleInputChange} nameObject={"programa"}/>
                </div>

                <div className={styles.containerSelect}>
                    <Select defaultValue={lancamento.unidade} model={data[3]} title={"Unidade *"} onChange={handleInputChange} nameObject={"unidade"}/>
                    <Select defaultValue={lancamento.grupoDespesa} model={data[4]} title={"Grupo Despesa *"} onChange={handleInputChange} nameObject={"grupoDespesa"}/>
                    <Select defaultValue={lancamento.unidadeOrcamentaria} model={data[5]} title={"Unidade Orçamentária *"} onChange={handleInputChange} nameObject={"unidadeOrcamentaria"}/>
                </div>

                <div className={styles.containerSelect}>
                    <Select defaultValue={lancamento.solicitante} model={data[6]} title={"Solicitante"} onChange={handleInputChange} nameObject={"solicitante"}/>
                    <Select defaultValue={lancamento.tipoLancamento} model={data[7]} title={"Tipo de Lançamento *"} onChange={handleInputChange} nameObject={"tipoLancamento"}/>
                    <Select defaultValue={lancamento.tipoTransacao} model={data[8]} title={"Tipo de Transação *"} onChange={handleInputChange} nameObject={"tipoTransacao"}/>
                </div>

                <div className={styles.containerSelect}>
                    <Select defaultValue={lancamento.objetivoEstrategico} model={data[9]} title={"Objetivo Estratégico"} onChange={handleInputChange} nameObject={"objetivoEstrategico"}/>
                    <Select defaultValue={lancamento.fonteRecurso} model={data[10]} title={"Fonte de Recurso *"} onChange={handleInputChange} nameObject={"fonteRecurso"}/>
                    <Select defaultValue={lancamento.modalidadeAplicacao} model={data[11]} title={"Modalidade de Aplicação *"} onChange={handleInputChange} nameObject={"modalidadeAplicacao"}/>
                </div>

                <div className={styles.containerSelect}>
                    <InputForm value={formData.numeroLancamento} title={"Número Lançamento *"} type="number" onChange={handleInputChange} nameObject="numeroLancamento"/>
                    <InputForm title={"Ano Orçamento *"} type="number" onChange={handleInputChange} nameObject="anoOrcamento"/>
                    <InputForm title={"Contratado *"} type="text" onChange={handleInputChange} nameObject="contratado"/>
                </div>

                <div className={styles.containerSelect}>
                    <Checkbox title={"Lançamento Inválido *"} onChange={handleInputChange} nameObject="lancamentoInvalido"/>
                    <Select model={data[12]} title={"Lançamento Pai"} onChange={handleInputChange} nameObject={"lancamentoPai"}/>
                    <InputForm title={"Descrição *"} type="text" onChange={handleInputChange} nameObject="descricao"/>
                </div>

                <div className={styles.containerSelect}>
                    <InputForm title={"Valor *"} type="number" onChange={handleInputChange} nameObject="valor"/>
                    <InputForm title={"GED *"} type="text" onChange={handleInputChange} nameObject="ged"/>
                    <InputForm title={"Data Lançamento *"} type="date" onChange={handleInputChange} nameObject="dataLancamento"/>
                </div>
        </ModalLancamentoUpdate>
       : null}

    
      <ToastContainer/>
    </Layout>
  )
}