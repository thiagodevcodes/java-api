import styles from "../styles/Modal.module.css";
import Button from "./Button";
import { useRouter } from "next/router";
import { handleCreate } from "@/services/axios";

export default function Modal({ title, controlModal, path, children, formData }) {
    const router = useRouter();

    const handleSubmit = (e) => {
        e.preventDefault();

        try {
            handleCreate(formData , path, router, controlModal);
        } catch (error) {
            console.error("Erro ao criar:", error);
        }
        
    };

    return (
        <div className={styles.modalOverlay}>
            <form className={styles.modal} onSubmit={handleSubmit} method="POST">
                <h2>{title}</h2>

                { children }

                <div className={styles.buttons}>
                    <Button type="submit" title="Salvar"/>
                    <Button title="Fechar" onClick={() => controlModal("post", false)}/>  
                </div>
            </form>
        </div>
    );
}