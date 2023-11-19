import styles from "../styles/Table.module.css"
import Link from "next/link"
import { useState } from "react";
import Modal from "./Modal";
import ModalUpdate from "./ModalUpdate";
import axios from "axios";
import { toast } from "react-toastify";

export default function Table({ acao }) {
    const [modalOpen, setModalOpen] = useState({ post: false, update: false });
    const [id, setId] = useState(null);

    const handleDelete = async(id) => {
        try {
            await axios.delete(`http://localhost:8080/api/orcamento/acao/${id}`);
            toast.success("Ação deletada com sucesso!");
        } catch (error) {
            //console.error('Erro ao buscar dados:', error);
            toast.error("Erro ao deletar a ação!");
        }
    }

    const abrirModal = (modal) => {
        if(modal == "post") {
            setModalOpen({ post: true, update: false });
        }
        if(modal == "update") {
            setModalOpen({ post: false, update: true });
        }
    };
  
    const fecharModal = () => {
        setModalOpen({ post: false, update: false });
    };

    return (
        <div className={styles.container}>
            <div className={styles.headerTable}>
                <div className={styles.headerButton}>
                    <img src="/icons/Action.svg" alt="" />
                    <h2>Tela de Ações</h2>
                </div>
                
                <button onClick={() => abrirModal("post")}>Adicionar</button>
                {modalOpen.post && <Modal title="Adicionar Ação" onClose={fecharModal} />}
                {modalOpen.update && <ModalUpdate id={id} title="Editar Ação" onClose={fecharModal}/>}
            </div>
            
            <table className={styles.table}>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome da Ação</th>
                    <th>Código</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                
                {acao.map((item) => (
                    <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.nome}</td>
                        <td>{item.codigo}</td>
                        <td className={styles.link}>
                            <Link href={`/acao/${item.id}`}>
                                <img src="/icons/Eye.svg" alt="" />
                            </Link>

                            <button className={styles.actionButton} onClick={() => {abrirModal("update"); setId(item.id); }}>
                                <img src="/icons/Edit.svg" alt="" />     
                            </button>
                            
                            <button onClick={() => handleDelete(item.id)} className={styles.actionButton}>
                                <img src="/icons/Delete.svg" alt="" />
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            
        </div>

    )
}

