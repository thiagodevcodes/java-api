import styles from "../styles/Modal.module.css";
import ModalButtons from "./ModalButtons";
import { useRouter } from "next/router";
import { handleCreate, getAllData } from "@/services/axios";

export default function ModalLancamento({ title, controlModal, path, children, formData }) {   
    const router = useRouter();

    const handleSubmit = (e) => {
        e.preventDefault();
        handleCreate(formData, path, router, controlModal);
    };

    return (
        <div className={styles.modalOverlay}>
            <form className={styles.modal} onSubmit={handleSubmit}>
                <h2>{title}</h2>

                { children } 

                <ModalButtons controlModal={() => controlModal("post", false)}/>
            </form>
        </div>
    );
}