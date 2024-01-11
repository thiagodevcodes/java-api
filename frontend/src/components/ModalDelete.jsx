import styles from "../styles/Modal.module.css";
import Button from "./Button";
import { useRouter } from "next/router";
import { handleDelete } from "@/services/axios";

export default function ModalDelete({ id, title, controlModal, path, children, formData }) {
    const router = useRouter();

    const handleSubmit = (e) => {
        e.preventDefault();

        try {
            handleDelete(id, path, router);
        } catch (error) {
            console.error("Erro ao deletar:", error);
        }
    };

    return (
        <div className={styles.modalOverlay}>
            <div className={styles.modal}>
                <h2>{title}</h2>
                <p>Tem certeza que quer excluir, <br /> essa ação não tem como ser revertida!</p>
                
                { children }
                
                <div className={styles.buttons}>
                    <Button title="Sim" onClick={handleSubmit}/>
                    <Button title="Não" onClick={() => controlModal("delete", false)}/>  
                </div>
            </div>
        </div>
    );
}