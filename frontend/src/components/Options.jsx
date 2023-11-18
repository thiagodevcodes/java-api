import Link from "next/link";
import styles from "../styles/Layout.module.css";

export default function Options(props) {
    return (
        <div className={styles.linkContainer}>
            <img src={props.img} alt="" />
            <Link href={props.href}>{props.title}</Link>
        </div>
    )
}