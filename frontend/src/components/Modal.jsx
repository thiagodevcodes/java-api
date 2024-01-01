import styles from "../styles/Modal.module.css";
import InputForm from "./InputForm";
import ModalButtons from "./ModalButtons";
import { useRouter } from "next/router";
import { useState } from "react";
import { handleCreate } from "@/services/axios";

export default function Modal({ title, controlModal, path, columns }) {
    const [codigo, setCodigo] = useState(null);
    const [nome, setNome] = useState("");
    const router = useRouter();

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
                    >
                    </InputForm>
                ))}

                <ModalButtons handle={() => handleCreate({nome, codigo}, path, router, controlModal)} controlModal={() => controlModal("post", false)}/>
            </div>
        </div>
    );
}