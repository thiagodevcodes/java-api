import Link from "next/link"
import styles from "../styles/Nav.module.css"

export default function Nav({ destino, cor, text }) {
    return (
        <Link href={destino}>
            <div className={styles.nav} style={{backgroundColor: cor ?? "dodgerblue"}}>
                {text}
            </div>
        </Link>
    )
}