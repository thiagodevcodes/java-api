import styles from "../styles/Layout.module.css";
import Link from "next/link"
import Options from "./Options";
import { useRouter } from 'next/router';


export default function Layout({ title, children}) {
    const router = useRouter();

    return (
        <div className={styles.container}>
            <header className={styles.layout}>
                <nav className={styles.nav}>
                    <div className={styles.logoContainer}>
                        <img src="/icons/favicon.svg" alt="" />
                        <h1>{title ?? "Orçamento Público"}</h1>
                    </div>
                    
                    <div>
                        <Link style={{margin: "20px"}} href="/">Home</Link>
                        <button onClick={() => router.back()}>Voltar</button>
                    </div>
                </nav>
            </header>

            <aside className={styles.layout}>
                <Options title="Home" img="/icons/Home.svg" href="/"/>
                <Options title="Acão" img="/icons/Action.svg" href="/acao"/>
                <Options title="Solicitante" img="/icons/Requesting.svg" href="/solicitante"/>
                <Options title="Programa" img="/icons/Program.svg" href="/programa"/>
                <Options title="Unidade" img="/icons/Unit.svg" href="/unidade"/>
                <Options title="Tipo de Lançamento" img="/icons/Posting.svg" href="/tipo-lancamento"/>
                <Options title="Elemento Despesa" img="/icons/Posting.svg" href="/elemento-despesa"/>
                <Options title="Fonte Recurso" img="/icons/Posting.svg" href="/fonte-recurso"/>
                <Options title="Grupo Despesa" img="/icons/Posting.svg" href="/grupo-despesa"/>
                <Options title="Modalidade Aplicação" img="/icons/Posting.svg" href="/modalidade-aplicacao"/>
                <Options title="Objetivo Estratégico" img="/icons/Posting.svg" href="/objetivo-estrategico"/>
                <Options title="Tipo de Transação" img="/icons/Posting.svg" href="/tipo-transacao"/>
                <Options title="Unidade Orçamentaria" img="/icons/Posting.svg" href="/unidade-orcamentaria"/>
            </aside>

            <main className={styles.layout}>
                {children} 
            </main>
            
            <footer className={styles.layout}>
            
            </footer>
        </div>
    )
}