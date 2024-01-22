import { useState, useEffect } from "react";
import styles from "../styles/Lancamento.module.css";

export default function Select({ model, title, onChange, defaultValue, year }) {
    const date = new Date()
    const [value, setValue] = useState(defaultValue);
 
    let arrayAnos = []

    for (let index = 0; index < 30; index++) {
        arrayAnos.push(date.getFullYear() + index)
    }

    useEffect(() => {
        if(defaultValue)
        setValue(defaultValue);
    }, [defaultValue]);

    return (
        <div className={styles.selectDiv}>
            <p>{title}</p>
            <select value={value} className={styles.select} onChange={onChange}>
                {
                    year ? null : <option className={styles.optionSelect} value={""} key={0}>Nenhum Selecionado</option>
                }

                {
                    year ?
                        arrayAnos.map((column) => {
                            return <option className={styles.optionSelect} value={column} key={column}>{column}</option>
                        }) : model ?  
                        model.map((column) => {
                            return <option className={styles.optionSelect} value={column.id} key={column.id}>{column.id} - {column.descricao ? column.descricao : column.nome}</option>
                        }) : null
                }
            </select>
        </div>
    );
}
