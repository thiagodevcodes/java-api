import styles from "../styles/Modal.module.css";
import ModalButtons from "./ModalButtons";
import { handleUpdate, getAllData, fetchDataById} from "@/services/axios";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";


export default function ModalUpdate({ title, id, controlModal, path, model, formData, setFormData, children}) {
    if (!model || model.length == 0) {
        return
    }

    const router = useRouter();

    useEffect(() => {
        getAllData().then((response) => {
            setFormData(response)
        })        
    },[])

    const handleSubmit = (e) => {
        e.preventDefault();
        handleUpdate(id, formData, path, router, controlModal);
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