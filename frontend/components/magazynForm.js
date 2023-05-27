import { useState } from 'react'

import styles from "./Form.module.css"

const MagazynForm = () => {
    const [inputs, setInputs] = useState([])

    const handleChange = (e) => {
        const name = e.target.name
        const value = e.target.value
        setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        await fetch(`http://localhost:8080/take/Hurtownia/Magazyn`, {
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
        <div className={styles.Div}>
        <h2 className={styles.text}>FORMULARZ DODAWANIA MAGAZYNU</h2>
        <form className={styles.Form} onSubmit={handleSubmit}>
            <label>Adres magazynu:</label>
            <input type="text" name="adres" value={inputs.adres || ""} onChange={handleChange} required={true}/>
            <label>Kierownik magazynu:</label>
            <input type="text" name="kierownik" value={inputs.kierownik || ""} onChange={handleChange} required={true}/>
            <input className={styles.Button} type="submit" value="DODAJ MAGAZYN"/>
        </form>
        </div>
    )
}

export { MagazynForm }