import Link from "next/link"
import styles from "../styles/Layout.module.css";
import { useRouter } from 'next/router';
import Options from "./Options";

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
            </aside>

            <main className={styles.layout}>
                {children} 
            </main>
            
            <footer className={styles.layout}>
            
            </footer>
        </div>
    )
}