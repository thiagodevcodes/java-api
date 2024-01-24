import Layout from "@/components/Layout/Layout"
import styles from "../styles/Home.module.css";
import { useEffect, useState } from "react";
import { fetchDataAll } from "@/services/axios";
import Image from "next/image";

export default function Home() {
    const [totalLancamento, setTotalLancamento] = useState(0)
    const [totalValor, setTotalValor] = useState((0).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }))

    useEffect(() => {
        fetchDataAll("lancamento").then((response) => {
            let total = 0;
            if(response) {
                console.log(response.length);
                setTotalLancamento(response.length)
            
                
                response.map((item) => {
                    console.log(item)
                    total += item.valor
                })

                
                setTotalValor(total.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' }));
                console.log(total)
            }
        })
    }, [])

    return (
        <Layout>
            <div className={styles.containerTitle}>
                <Image src={"/icons/Home.svg"} alt="" width={30} height={30}/>
                <h1 className={styles.title}>Tela Incial</h1>
            </div>
            
            <div className={styles.container}>   
                <div className={styles.chield}>
                    <h2>Total de Lançamentos</h2>
                    <p>{totalLancamento}</p>
                </div>

                <div className={styles.chield}>
                    <h2>Valor Total</h2>
                    <p>{totalValor}</p>
                </div>
            </div>
        </Layout>
    )
}