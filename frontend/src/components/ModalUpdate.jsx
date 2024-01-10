import styles from "../styles/Modal.module.css";
import ModalButtons from "./ModalButtons";
import { handleUpdate, fetchDataById } from "@/services/axios";
import { useEffect } from "react";
import { useRouter } from "next/router";


export default function ModalUpdate({ title, id, controlModal, path, model, children, setFormData, formData }) {
    const router = useRouter();

    useEffect(() => {
        fetchDataById(id, path).then((response) => {
            setFormData( response )
            console.log(response);
        }).catch((error) => {
            console.error(error)
        })
    }, [id]);

    if (!model || model.length == 0) {
        return 
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        handleUpdate(id, formData, path, router, controlModal);
    };

    return (
        <div className={styles.modalOverlay}>
            <form className={styles.modal} onSubmit={handleSubmit}>
                <h2>{title}</h2>

                { children }

                <ModalButtons  controlModal={() => controlModal("update", false)}/>
            </form>
        </div>
    );
}