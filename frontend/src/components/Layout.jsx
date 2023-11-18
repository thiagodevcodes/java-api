import Link from "next/link"
import styles from "../styles/Layout.module.css";
import { useRouter } from 'next/router';
import Options from "./Options";

export default function Layout(props) {
    const router = useRouter();

    return (
        <div className={styles.container}>
            <header className={styles.layout}>
                <nav className={styles.nav}>
                    <h1>{props.titulo ?? "Orçamento Público"}</h1>
                    <div>
                        <Link style={{margin: "20px"}} href="/">Home</Link>
                        <button onClick={() => router.back()}>Voltar</button>
                    </div>
                </nav>
            </header>

            <aside className={styles.layout}>
                <Options title="Home" img="/icons/Home.png" href="/"/>
                <Options title="Acão" img="/icons/Home.png" href="/acao"/>
            </aside>

            <main className={styles.layout}>
                {props.children} 
            </main>
            {/*
            <footer className={styles.layout}>
                Footer
            </footer>
            */}
        </div>
    )
}