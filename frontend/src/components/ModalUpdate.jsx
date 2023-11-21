import styles from "../styles/Modal.module.css";
import InputForm from "./InputForm";
import ModalButtons from "./ModalButtons";
import { handleUpdate, fetchDataById } from "@/services/axios";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";


export default function ModalUpdate({ title, id, controlModal, path, model}) {
    const [codigo, setCodigo] = useState("");
    const [nome, setNome] = useState("");

    if (!model || model.length == 0) {
        return 
    }

    const router = useRouter();

    const columns = Object.keys(model[0]);
    columns.shift();

    useEffect(() => {
        fetchDataById(id, path).then((response) => {
            if(columns.includes("codigo")) setCodigo(response.codigo);
            setNome(response.nome);
                
        }).catch((error) => {
            console.error(error)
        })

    }, [id]);

    return (
        <div className={styles.modalOverlay}>
            <div className={styles.modal}>
                <h2>{title}</h2>
 
                {columns.map((column) => (
                    <InputForm         
                        key={column}    
                        id={column}
                        type={typeof(column) == "number" ? "number" : "text"}
                        title={column.charAt(0).toUpperCase() + column.slice(1) + ":"}
                        htmlFor={column}
                          
                        onChange={(e) => {
                            if (column === 'codigo') {
                              setCodigo(e.target.value);
                            } else if (column === 'nome') {
                              setNome(e.target.value);
                            }
                        }}
                        value={ column === 'nome' ? nome : codigo}
                    >
                    </InputForm>
                ))}

                <ModalButtons 
                    handle={() => handleUpdate(id, { nome, codigo }, path, router, controlModal)} 
                    controlModal={() => controlModal("update", false)}
                />
            </div>
        </div>
    );
}