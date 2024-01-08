import styles from "../styles/Modal.module.css";
import { useState } from "react";

export default function Checkbox({ title, onChange, nameObject }) {
    const [isChecked, setIsChecked] = useState(false);

    const handleCheckboxChange = (event) => {
        const novoValor = event.target.checked;
        setIsChecked(novoValor);
  
        // Chama a função de callback fornecida pelo componente pai
        if (onChange) {
            onChange(nameObject, novoValor);
        }
    };

    return (
        <div>
            <label>
                {title}
                <input
                    type="checkbox"
                    checked={isChecked}
                    onChange={handleCheckboxChange}
                />
            </label>
      </div>
    )
}