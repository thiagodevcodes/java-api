import styles from "../styles/Modal.module.css";

export default function ModalButtons({ handle, controlModal }) {    
    return (
        <div className={styles.buttons}>
            <button onClick={() => controlModal()}>Fechar</button>
            <button onClick={() => handle()}>Salvar</button>
        </div>
    )
}