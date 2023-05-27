import { useState, useEffect } from 'react'
import Box from '@mui/material/Box'
import Modal from '@mui/material/Modal'

import styles2 from "../src/app/page.module.css"
import styles from "./Form.module.css"

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
    width: '50vw',
  height: '80vh',
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
    p: 4,
    overflowY: "scroll"
};

const UpdateModal = ({ magazynID }) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
    
    const [inputs, setInputs] = useState([])

    const handleChange = (e) => {
        const name = e.target.name
        const value = e.target.value
        setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        await fetch(`http://localhost:8080/take/Hurtownia/Magazyn/${magazynID}`, {
            method: "PUT",
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
    <>
      <button onClick={handleOpen} className={styles2.Button}>Modyfikuj</button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
            <div className={styles.Div}>
            <h2 className={styles.text}>FORMULARZ MODYFIKOWANIA MAGAZYNU</h2>
            <form className={styles.Form} onSubmit={handleSubmit}>
            <label>Adres magazynu:</label>
            <input type="text" name="adres" value={inputs.adres || ""} onChange={handleChange} required={true}/>
            <label>Kierownik magazynu:</label>
            <input type="text" name="kierownik" value={inputs.kierownik || ""} onChange={handleChange} required={true}/>
            <input className={styles.Button} type="submit" value="MODYFIKUJ MAGAZYN"/>
        </form>
        </div>
        </Box>
      </Modal>
    </>
  );
}

export {UpdateModal}