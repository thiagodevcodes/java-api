import { useState, useEffect } from "react";
import styles from "../styles/Lancamento.module.css";

export default function Select({ model, title, onChange, defaultValue, year }) {
    const [value, setValue] = useState(defaultValue);

    useEffect(() => {
        setValue(defaultValue);
    }, [defaultValue]);

    if (!model) return

    return (
        <div className={styles.selectDiv}>
            <p>{title}</p>
            <select value={value} className={styles.select} onChange={onChange}>
                {
                    year ? null : <option className={styles.optionSelect} value={null} key={0}>Nenhum Selecionado</option>
                }

                {
                    year ?
                        model.map((column) => {
                            return <option className={styles.optionSelect} value={column} key={column}>{column}</option>
                        }) :
                        model.map((column) => {
                            return <option className={styles.optionSelect} value={column.id} key={column.id}>{column.id} - {column.descricao ? column.descricao : column.nome}</option>
                        })
                }
            </select>
        </div>
    );
}
