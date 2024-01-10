import styles from "../styles/Layout.module.css";
import Link from "next/link";
import Image from "next/image";

export default function Options({ img, href, title }) {
    return (
        <div className={styles.linkContainer}>
            <Image src={img} alt="" width={30} height={30}/>
            <Link href={href}>{title}</Link>
        </div>
    )
}