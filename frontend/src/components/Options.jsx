import Link from "next/link";
import styles from "../styles/Layout.module.css";

export default function Options({ img, href, title }) {
    return (
        <div className={styles.linkContainer}>
            <img src={img} alt="" />
            <Link href={href}>{title}</Link>
        </div>
    )
}