import styles from "../styles/Modal.module.css";

export default function InputForm({htmlFor, title, id, onChange, type, value}) {
    return (
        <div className={styles.inputContainer}>
            <label htmlFor={htmlFor}>{title} </label>
            <input id={id} onChange={onChange} type={type} value={value}/>
        </div>
    )
}