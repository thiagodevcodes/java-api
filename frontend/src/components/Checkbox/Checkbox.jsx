import { useState } from "react";

export default function Checkbox({ title, onChange, nameObject }) {
    const [isChecked, setIsChecked] = useState(false);

    const handleCheckboxChange = (event) => {
        const novoValor = event.target.checked || false;
        setIsChecked(novoValor);
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