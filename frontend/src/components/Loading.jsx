import styles from "../styles/Loader.module.css";
import Image from "next/image";

export default function Loading() {
    return (
        <div className={styles.loaderContainer}>
            <Image alt="Loader" src={"/icons/Loader.svg"} width={10} height={10}/>
        </div>
    )
}