import styles from "../styles/Modal.module.css";
import Button from "./Button";
import { handleUpdate, fetchDataById } from "@/services/axios";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import Loading from "./Loading";


export default function ModalUpdate({ title, id, controlModal, path, children, setFormData, formData }) {
    const router = useRouter();
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchDataById(id, path).then((response) => {
            setFormData(response)
            setLoading(true)
        }).catch((error) => {
            console.error(error)
        })
    }, [id, setFormData, path]);

    const handleSubmit = (e) => {
        e.preventDefault();
        handleUpdate(id, formData, path, router, controlModal);
    };

    return (
        <div className={styles.modalOverlay}>
            { loading && 
            <form className={styles.modal} onSubmit={handleSubmit}>
                <h2>{title}</h2>

                {children}
                
                <div className={styles.buttons}>
                    <Button type="submit" title="Salvar" />
                    <Button title="Fechar" onClick={() => controlModal("update", false)} />
                </div>
            </form>
            }
            {!loading && <Loading/>}
        </div>
    );
}