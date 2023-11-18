import styles from "../styles/Modal.module.css";

export default function InputForm(props) {
    return (
        <div className={styles.inputContainer}>
            <label htmlFor={props.htmlFor}>{props.title} </label>
            <input id={props.id} onChange={props.onChange} type={props.type} value={props.value}/>
        </div>
    )
}