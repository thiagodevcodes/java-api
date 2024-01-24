import styles from "./Button.module.css";

export default function Button({ type, onClick, title }) {
    return (
        <button className={styles.button} onClick={onClick} type={type}>
            <p>{title}</p>
        </button>
    )
}