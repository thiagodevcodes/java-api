import { useState, useEffect } from "react";
import styles from "../styles/Modal.module.css";

export default function Select({ model, title, onChange, nameObject, defaultValue }) {
    if(!model) return

    return (
        <div className={styles.selectDiv}>
            <p>{title}</p>
            <select value={defaultValue} className={styles.select} onChange={onChange}>
                <option className={styles.optionSelect} value={0} key={0}>Nenhum Selecionado</option>
                { 
                    model.map((column) => { 
                        return <option className={styles.optionSelect} value={column.id} key={column.id}>{column.id} - {column.descricao ? column.descricao : column.nome}</option>
                    })
                }
            </select>
        </div>
    );
}
