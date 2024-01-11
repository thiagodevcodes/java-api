import styles from "../styles/Layout.module.css";
import Link from "next/link"
import Options from "./Options";
import { useRouter } from 'next/router';
import Image from "next/image";


export default function Layout({ title, children }) {
    const router = useRouter();

    return (
        <div className={styles.container}>
            <header className={styles.layout}>
                <nav className={styles.nav}>
                    <div className={styles.logoContainer}>
                        <Image src={"/icons/favicon.svg"} alt="" width={30} height={30}></Image>
                        <h1>{title ?? "Orçamento Público"}</h1>
                    </div>

                    <div>
                        <Link style={{ margin: "20px" }} href="/">Home</Link>
                        <button onClick={() => router.back()}>Voltar</button>
                    </div>
                </nav>
            </header>

            <aside className={styles.layout}>
                <Options title="Home" img="/icons/Home.svg" href="/" />
                <Options title="Lançamento" img="/icons/Unity.svg" href="/lancamento" />
                <Options title="Acão" img="/icons/Action.svg" href="/acao" />
                <Options title="Solicitante" img="/icons/Requesting.svg" href="/solicitante" />
                <Options title="Programa" img="/icons/Program.svg" href="/programa" />
                <Options title="Unidade" img="/icons/Unit.svg" href="/unidade" />
                <Options title="Tipo de Lançamento" img="/icons/Posting.svg" href="/tipo-lancamento" />
                <Options title="Elemento Despesa" img="/icons/Cost.svg" href="/elemento-despesa" />
                <Options title="Fonte Recurso" img="/icons/Bonds.svg" href="/fonte-recurso" />
                <Options title="Grupo Despesa" img="/icons/People.svg" href="/grupo-despesa" />
                <Options title="Modalidade Aplicação" img="/icons/Transfer.svg" href="/modalidade-aplicacao" />
                <Options title="Objetivo Estratégico" img="/icons/Strategy.svg" href="/objetivo-estrategico" />
                <Options title="Tipo de Transação" img="/icons/Transaction.svg" href="/tipo-transacao" />
                <Options title="Unidade Orçamentaria" img="/icons/Unity.svg" href="/unidade-orcamentaria" />
            </aside>

            <main className={styles.layout}>
                {children}
            </main>

            <footer className={styles.layout}>

            </footer>
        </div>
    )
}