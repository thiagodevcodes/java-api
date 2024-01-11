import styles from "../styles/Table.module.css"
import Link from "next/link"
import { useRouter } from "next/router";
import { handleDelete } from "@/services/axios";
import Image from "next/image";

export default function Table({ model, controlModal, setId, path, columns }) {
    const router = useRouter();

    return (
        <div className={styles.container}>
            <div className={styles.container2}>


                <table className={styles.table}>
                    <thead>
                        <tr>
                            {columns.map((column) => (

                                <th key={column.cod}>{column.name}</th>
                            ))}
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>

                        {
                            model.length == 0 ?
                                <tr>
                                    <td colSpan={50}>
                                        Não possui dados!
                                    </td>
                                </tr>
                                :
                                model.map((row, index) => (
                                    <tr key={index}>
                                        {columns.map((column) => (

                                            <td key={column.cod}>{row[column.cod]}</td>
                                        ))}
                                        <td className={styles.link}>
                                            {
                                                path == "lancamento" ?
                                                    <Link href={`/${path}/${row.id}`}>
                                                        <Image src={"/icons/Eye.svg"} alt="" width={30} height={30}></Image>
                                                    </Link>
                                                    : null
                                            }

                                            <button className={styles.actionButton} onClick={() => { controlModal("update", true); setId(row.id); }}>
                                                <Image src={"/icons/Edit.svg"} alt="" width={30} height={30} />
                                            </button>

                                            <button className={styles.actionButton} onClick={() => { controlModal("delete", true); setId(row.id); }}>
                                                <Image src={"/icons/Delete.svg"} alt="" width={30} height={30} />
                                            </button>
                                        </td>
                                    </tr>
                                ))
                        }

                    </tbody>
                </table>
            </div>
        </div>
    )
}

