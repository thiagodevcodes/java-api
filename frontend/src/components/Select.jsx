import { useState, useEffect } from "react";
import styles from "../styles/Lancamento.module.css";

export default function Select({ model, title, onChange, defaultValue }) {
    const [value, setValue] = useState(defaultValue);

    if(!model) return

    useEffect(() => {
        setValue(defaultValue);
    }, [defaultValue]);

    return (
        <div className={styles.selectDiv}>
            <p>{title}</p>
            <select value={value} className={styles.select} onChange={onChange}>
                <option className={styles.optionSelect} value={null} key={0}>Nenhum Selecionado</option>
                { 
                    model.map((column) => { 
                        return <option className={styles.optionSelect} value={column.id} key={column.id}>{column.id} - {column.descricao ? column.descricao : column.nome}</option>
                    })
                }
            </select>
        </div>
    );
}
