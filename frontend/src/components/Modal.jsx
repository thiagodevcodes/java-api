import { useState } from "react";
import styles from "../styles/Modal.module.css";
import axios from "axios";
import { useRouter } from 'next/router';
import InputForm from "./InputForm";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function Modal(props) {
    const [codigo, setCodigo] = useState(null);
    const [nome, setNome] = useState("");
    const router = useRouter();

    const handleCreate = async (data) => {
        try {
            await axios
                .post("http://localhost:8080/api/orcamento/acao", data)
                .then((response) => {
                    setTimeout(() => {
                        router.reload();
                    }, 3000);
                    toast.success('Ação foi cadastrada.')
                    //timeoutId();
                    //console.log(response);
              })
              .catch((error) => {
                //console.log(error);
                toast.error('Ocorreu um erro ao cadastrar!')
                
              });
            } catch (error) {
            //console.error(error);
            toast.error('Ocorreu um erro ao cadastrar!')
        }
    }

    return (
        <div className={styles.modalOverlay}>
            <div className={styles.modal}>
                <h2>{props.title}</h2>

                <InputForm 
                    id="inputNome" 
                    type="number"
                    title="Código:"
                    htmlFor="inputCodigo"
                    onChange={(e) => setCodigo(e.target.value)}>
                </InputForm>

                <InputForm 
                    id="inputNome" 
                    type="text"
                    title="Nome:"
                    htmlFor="inputNome"
                    onChange={(e) => setNome(e.target.value)}>
                </InputForm>
                
                <div className={styles.buttons}>
                    <button onClick={props.onClose}>Fechar</button>
                    <button onClick={() => handleCreate({nome, codigo})}>Salvar</button>
                </div>

                <ToastContainer/>
            </div>
        </div>
    );
}