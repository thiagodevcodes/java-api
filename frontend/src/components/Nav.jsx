import Link from "next/link"
import styles from "../styles/Nav.module.css"

export default function Nav(props) {
    return (
        <Link href={props.destino}>
            <div className={styles.nav} style={{backgroundColor: props.cor ?? "dodgerblue"}}>
                {props.text}
            </div>
        </Link>
    )
}