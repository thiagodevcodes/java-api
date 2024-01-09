import Layout from "@/components/Layout"
import Link from "next/link"
import styles from "../styles/Home.module.css";

export default function Home() {
    return (
        <Layout>
            <div className={styles.containerTitle}>
                <img src="/icons/Home.svg" alt="" />
                <h1 className={styles.title}>Tela Incial</h1>
            </div>
            
            <div className={styles.container}>   
                <Link href="/lancamento">
                    <div className={styles.chield}>
                        <img src="/icons/Unity.svg" alt="" />
                        <p>Lançamentos</p>
                    </div>
                </Link>
                <Link href="/acao">
                    <div className={styles.chield}>
                        <img src="/icons/Action.svg" alt="" />
                        <p>Ação</p>
                    </div>
                </Link>
                <Link href="/solicitante">
                    <div className={styles.chield}>
                        <img src="/icons/Requesting.svg" alt="" />
                        <p>Solicitante</p>
                    </div>
                </Link>
                <Link href="/programa">
                    <div className={styles.chield}>
                        <img src="/icons/Program.svg" alt="" />
                        <p>Programa</p>
                    </div>
                </Link>
                <Link href="/unidade">
                    <div className={styles.chield}>
                        <img src="/icons/Unit.svg" alt="" />
                        <p>Unidade</p>
                    </div>
                </Link>
                <Link href="/tipo-lancamento">
                    <div className={styles.chield}>
                        <img src="/icons/Posting.svg" alt="" />
                        <p>Tipo de Lançamento</p>
                    </div>
                </Link>
                <Link href="/elemento-despesa">
                    <div className={styles.chield}>
                        <img src="/icons/Cost.svg" alt="" />
                        <p>Elemento Despesa</p>
                    </div>
                </Link>
                <Link href="/fonte-recurso">
                    <div className={styles.chield}>
                        <img src="/icons/Bonds.svg" alt="" />
                        <p>Fonte Rercurso</p>
                    </div>
                </Link>
                <Link href="/grupo-despesa">
                    <div className={styles.chield}>
                        <img src="/icons/People.svg" alt="" />
                        <p>Grupo Despesa</p>
                    </div>
                </Link>
                <Link href="/modalidade-aplicacao">
                    <div className={styles.chield}>
                        <img src="/icons/Transfer.svg" alt="" />
                        <p>Modalidade de Aplicação</p> 
                    </div>
                </Link>
                <Link href="/objetivo-estrategico">
                    <div className={styles.chield}>
                        <img src="/icons/Strategy.svg" alt="" />
                        <p>Objetivo Estratégico</p>
                    </div>
                </Link>
                <Link href="/tipo-transacao">
                    <div className={styles.chield}>
                        <img src="/icons/Transaction.svg" alt="" />
                        <p>Tipo de Transação</p>
                    </div>
                </Link>
                <Link href="/unidade-orcamentaria">
                    <div className={styles.chield}>
                        <img src="/icons/Unity.svg" alt="" />
                        <p>Unidade Orçamentária</p>
                    </div>
                </Link>
            </div>
        </Layout>
    )
}