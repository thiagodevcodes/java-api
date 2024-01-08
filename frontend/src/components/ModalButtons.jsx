import styles from "../styles/Modal.module.css";

export default function ModalButtons({ controlModal }) {    
    return (
        <div className={styles.buttons}>
            <button onClick={() => controlModal()}>Fechar</button>
            <button type="submit">Salvar</button>
        </div>
    )
}