import styles from "../styles/Table.module.css"
import Link from "next/link"
import { useRouter } from "next/router";
import { handleDelete } from "@/services/axios";

export default function Table({ model, controlModal, setId, path, columns }) {
    const router = useRouter();

    return (
        <div className={styles.container}>
            <table className={styles.table}>
                <thead>
                <tr>
                {columns.map((column) => (

                    <th key={column}>{column.charAt(0).toUpperCase() + column.slice(1)}</th>
                ))}
                <th>Ações</th>
                </tr>
                </thead>
                <tbody>

                {
                model.length == 0 ? 
                <tr>
                    <td colSpan={4}>
                        Não possui dados!
                    </td>
                </tr>
                    : 
                model.map((row, index) => (
                    <tr key={index}>
                        {columns.map((column) => (

                            <td key={column}>{row[column]}</td>
                        ))}
                        <td className={styles.link}>
                            <Link href={`/${path}/${row.id}`}>
                                <img src="/icons/Eye.svg" alt="" />
                            </Link>

                            <button className={styles.actionButton} onClick={() => {controlModal("update", true); setId(row.id); }}>
                                <img src="/icons/Edit.svg" alt="" />     
                            </button>
                                
                            <button onClick={() => handleDelete(row.id, path, router)} className={styles.actionButton}>
                                <img src="/icons/Delete.svg" alt="" />
                            </button>
                        </td>
                    </tr>
                    ))
                }

                </tbody>
            </table>
        </div>
    )
}

