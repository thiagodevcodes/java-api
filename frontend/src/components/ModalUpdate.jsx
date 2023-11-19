import { useEffect, useState } from "react";
import styles from "../styles/Modal.module.css";
import axios from "axios";
import InputForm from "./InputForm";
import { toast } from "react-toastify";


export default function ModalUpdate({ title, id, onClose }) {
    const [acao, setAcao] = useState({
        codigo: "",
        nome: ""
    });

    const [codigo, setCodigo] = useState(acao.codigo);
    const [nome, setNome] = useState(acao.nome);


    const fetchDataById = async (id) => {
        try {
          const response = await axios.get(`http://localhost:8080/api/orcamento/acao/${id}`);
          setAcao(response.data);
        } catch (error) {
          //console.error('Erro ao buscar dados da ação:', error);
          toast.error("Erro ao sincronizar dados da ação!")
          setAcao({ codigo: "", nome: "" });
        }
    };

    const handleUpdate = async (id, data) => {
        try {
            
            if(data) {
                await axios
                .put(`http://localhost:8080/api/orcamento/acao/${id}`, data)
                .then((response) => {
                    toast.success("Ação atualizada com sucesso!")
                    //console.log(response);
                })
                .catch((error) => {
                //console.log(error);
                toast.error("Ocorreu um eror ao atualizar!")
              });
            }
            } catch (error) {
            //console.error(error);
            toast.error("Ocorreu um eror ao atualizar!")
        }
    }

    useEffect(() => {
        fetchDataById(id);
    }, [id])

    useEffect(() => {
        if (acao && acao.codigo && acao.nome) {
            setCodigo(acao.codigo);
            setNome(acao.nome);
        }
    }, [acao]);

    return (
        <div className={styles.modalOverlay}>
            <div className={styles.modal}>
                <h2>{title}</h2>
 
                <InputForm 
                    id="inputCodigo" 
                    onChange={(e) => setCodigo(e.target.value)} 
                    type="number"
                    value={codigo}
                    title="Código:"
                    htmlFor="inputCodigo">
                </InputForm>

                <InputForm 
                    id="inputNome" 
                    onChange={(e) => setNome(e.target.value)} 
                    type="text"
                    value={nome}
                    title="Nome:"
                    htmlFor="inputNome">
                </InputForm>
        
                <div className={styles.buttons}>
                    <button onClick={onClose}>Fechar</button>
                    <button onClick={() => handleUpdate(id, { nome, codigo })}>Salvar</button>
                </div>
            </div>
        </div>
    );
}