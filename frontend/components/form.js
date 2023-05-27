import { useState } from 'react'

import styles from "./Form.module.css"

const Form = ({ magazynID }) => {
    const [inputs, setInputs] = useState([])

    const handleChange = (e) => {
        const name = e.target.name
        const value = e.target.value
        setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        await fetch(`http://localhost:8080/take/Hurtownia/Magazyn/${magazynID}/Produkt`, {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(inputs)
        })
        window.location.reload()
    } catch (error) {
        console.error(error);
    }
}

    return (
        <form className={styles.Form} onSubmit={handleSubmit}>
            <label>Nazwa produktu:</label>
            <input type="text" name="nazwa" value={inputs.nazwa || ""} onChange={handleChange} required={true}/>
            <label>Kategoria produktu:</label>
            <input type="text" name="kategoria" value={inputs.kategoria || ""} onChange={handleChange} required={true}/>
            <label>Producent:</label>
            <input type="text" name="producent" value={inputs.producent || ""} onChange={handleChange} required={true}/>
            <label>Cena:</label>
            <input type="number" name="cena" value={inputs.cena || 0} onChange={handleChange} required={true}/>
            <input className={styles.Button} type="submit" value="DODAJ PRODUKT"/>
        </form>
    )
}

export { Form }