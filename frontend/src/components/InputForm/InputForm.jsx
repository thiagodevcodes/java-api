import styles from "./InputForm.module.css";

export default function InputForm({ htmlFor, title, id, onChange, type, min, value }) {
    return (
        <div className={styles.inputContainer}>
            <label htmlFor={htmlFor}>{title} </label>
            <input id={id} onChange={onChange} type={type} value={value} min={min} />
        </div>
    )
}