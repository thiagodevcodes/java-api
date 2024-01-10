import styles from "../styles/Table.module.css";
import Image from "next/image";

export default function Header({ controlModal, title, img }) {
    return (
        <div className={styles.headerTable}>
            <div className={styles.headerButton}>
                <Image src={img} alt="" width={30} height={30}></Image>
                <h2>Tela de {title}</h2>
            </div>
            
            <button onClick={() => controlModal("post", true)}>Adicionar</button>
        </div>
    );
}