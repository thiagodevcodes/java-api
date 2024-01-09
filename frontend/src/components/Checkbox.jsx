import styles from "../styles/Lancamento.module.css";
import { useState, useEffect } from "react";

export default function Checkbox({ title, onChange, nameObject, value }) {
    const [isChecked, setIsChecked] = useState(false);

    const handleCheckboxChange = (event) => {
        const novoValor = event.target.checked;
        setIsChecked(novoValor);
        if (onChange) {
            onChange(nameObject, novoValor);
        }
    };

    useEffect(() => {
        setIsChecked(value);
    }, [value]);

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