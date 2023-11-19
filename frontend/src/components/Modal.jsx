import { useState } from "react";
import styles from "../styles/Modal.module.css";
import axios from "axios";
import InputForm from "./InputForm";
import { toast } from "react-toastify";

export default function Modal({ title, onClose }) {
    const [codigo, setCodigo] = useState(null);
    const [nome, setNome] = useState("");

    const handleCreate = async (data) => {
        try {
            await axios
                .post("http://localhost:8080/api/orcamento/acao", data)
                .then(() => {
                    toast.success('Ação foi cadastrada.')
                    //console.log(response);
              })
              .catch(() => {
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
                <h2>{title}</h2>

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
                    <button onClick={onClose}>Fechar</button>
                    <button onClick={() => handleCreate({nome, codigo})}>Salvar</button>
                </div>
            </div>
        </div>
    );
}