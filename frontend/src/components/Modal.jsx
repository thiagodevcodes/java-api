import { useState } from "react";
import styles from "../styles/Modal.module.css";
import axios from "axios";
import InputForm from "./InputForm";
import { toast } from "react-toastify";
import { useRouter } from "next/router";

export default function Modal({ title, controlModal }) {
    const [codigo, setCodigo] = useState(null);
    const [nome, setNome] = useState("");
    const router = useRouter();

    const handleCreate = async (data) => {
        try {       
            if(!data.nome || !data.codigo) return toast.warn("Insira os campos corretamente!")
            
            await axios
                .post("http://localhost:8080/api/orcamento/acao", data)
                .then(() => {
                    controlModal("post", false)
                    setTimeout(() => {
                        router.reload();
                    }, 3000)    
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
                    <button onClick={() => controlModal("post", false)}>Fechar</button>
                    <button onClick={() => handleCreate({nome, codigo})}>Salvar</button>
                </div>
            </div>
        </div>
    );
}