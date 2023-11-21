import styles from "../styles/Layout.module.css";
import Link from "next/link";

export default function Options({ img, href, title }) {
    return (
        <div className={styles.linkContainer}>
            <img src={img} alt="" />
            <Link href={href}>{title}</Link>
        </div>
    )
}