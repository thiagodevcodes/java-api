import styles from "../styles/Table.module.css"
import Link from "next/link"
import axios from "axios";
import { toast } from "react-toastify";

export default function Table({ acao, controlModal, setId }) {
    const handleDelete = async(id) => {
        try {
            await axios.delete(`http://localhost:8080/api/orcamento/acao/${id}`);
            toast.success("Ação deletada com sucesso!");
        } catch (error) {
            //console.error('Erro ao buscar dados:', error);
            toast.error("Erro ao deletar a ação!");
        }
    }

    return (
        <div className={styles.container}>
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

                {
                acao.length == 0 ? 
                <tr style={{}}>
                    <td colSpan={4}>
                        Não possui dados!
                    </td>
                </tr>
                    : 
                acao.map((item) => (
                    <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.nome}</td>
                        <td>{item.codigo}</td>
                        <td className={styles.link}>
                            <Link href={`/acao/${item.id}`}>
                                <img src="/icons/Eye.svg" alt="" />
                            </Link>

                            <button className={styles.actionButton} onClick={() => {controlModal("update", true); setId(item.id); }}>
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

