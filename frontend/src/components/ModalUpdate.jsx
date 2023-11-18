import { useEffect, useState } from "react";
import styles from "../styles/Modal.module.css";
import axios from "axios";
import { useRouter } from 'next/router';
import InputForm from "./InputForm";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function ModalUpdate(props) {
    const [acao, setAcao] = useState({})
    const [codigo, setCodigo] = useState(acao?.codigo || "");
    const [nome, setNome] = useState(acao?.nome || "");
    const router = useRouter();


    const fetchDataById = async (id) => {
        try {
          const response = await axios.get(`http://localhost:8080/api/orcamento/acao/${id}`);
          setAcao(response.data);
        } catch (error) {
          //console.error('Erro ao buscar dados da ação:', error);
          toast.error("Erro ao sincronizar dados da ação!")
          setAcao(null);
        }
    };

    const handleUpdate = async (id, data) => {
        try {
            if(data) {
                await axios
                .put(`http://localhost:8080/api/orcamento/acao/${id}`, data)
                .then((response) => {
                    setTimeout(() => {
                        router.reload();
                    }, 3000);
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
        fetchDataById(props.id);
    }, [props.id])

    useEffect(() => {
        if (acao && acao.codigo && acao.nome) {
            setCodigo(acao.codigo);
            setNome(acao.nome);
        }
    }, [acao]);

    return (
        <div className={styles.modalOverlay}>
            <div className={styles.modal}>
                <h2>{props.title}</h2>
 
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
                    <button onClick={props.onClose}>Fechar</button>
                    <button onClick={() => handleUpdate(props.id, { nome, codigo })}>Salvar</button>
                </div>

                <ToastContainer/>
            </div>
        </div>
    );
}