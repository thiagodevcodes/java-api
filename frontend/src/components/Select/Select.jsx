import { useState, useEffect } from "react";
import styles from "./Select.module.css";
import { fetchDataById } from "@/services/axios";

export default function Select({ model, title, onChange, value, setValue, id, year }) {
    const date = new Date()
    let arrayAnos = []

    for (let index = 0; index < 30; index++) {
        arrayAnos.push(date.getFullYear() + index)
    }

    useEffect(() => {
        if(!id) return
    
        fetchDataById(id, "lancamento/all").then((res) => {
          setValue(res)
        }).catch((err) => {
          console.log(err)
        })
      }, [id])

    return (
        <div className={styles.selectDiv}>
            <p>{title}</p>
            <select value={value ? value : ""} className={styles.select} onChange={onChange}>            
                { !year && <option className={styles.optionSelect} value={""} key={0}>Nenhum Selecionado</option> }

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
