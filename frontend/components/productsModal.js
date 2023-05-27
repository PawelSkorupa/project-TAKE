import { useState, useEffect } from 'react'
import Box from '@mui/material/Box'
import Modal from '@mui/material/Modal'

import styles2 from "../src/app/page.module.css"
import {Form} from '../components/form.js'

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

const ProductsModal = ({ magazynID }) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
    
    const [data, setData] = useState([]);

    useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/take/Hurtownia/Magazyn/${magazynID}/Produkty`);
        const actualData = await response.json();
        setData(actualData);
        console.log(actualData);
      } catch (err) {
        console.log(err.message);
      }
    };

        if (open) {
            fetchData();
    }

  }, [open]);

    const handleDelete = (magazynID, produktID) => {
    try {
        fetch(`http://localhost:8080/take/Hurtownia/Magazyn/${magazynID}/Produkt/${produktID}`, {
            method: "DELETE",
            mode: "cors",
        })
        window.location.reload()
    } catch (error) {
        console.error(error);
    }
  }
    
  return (
    <>
      <button onClick={handleOpen} className={styles2.Button}>Produkty</button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
            <h1 className={styles2.text}>PRODUKTY MAGAZYNU ID = {magazynID}</h1>
            <table className={styles2.table}>
      <tbody>
        <tr className={styles2.table}>
          <th className={styles2.table}>id</th>
          <th className={styles2.table}>nazwa</th>
          <th className={styles2.table}>kategoria</th>
          <th className={styles2.table}>producent</th>
          <th className={styles2.table}>cena</th>
          <th className={styles2.table}>akcje</th>
        </tr>
        {data.map((item, index) => (
          <tr className={styles2.table} key={index}>
            <td className={styles2.table}>{item.id}</td>
            <td className={styles2.table}>{item.nazwa}</td>
            <td className={styles2.table}>{item.kategoria}</td>
            <td className={styles2.table}>{item.producent}</td>
            <td className={styles2.table}>{item.cena} zł</td>
            <td className={styles2.table}>
              <button className={styles2.Button} onClick={() => handleDelete(magazynID, item.id)}>Usuń z magazynu</button>
            </td>
          </tr>
        ))}
        </tbody>
        </table>
        <h2 className={styles2.text}>FORMULARZ DODAWANIA PRODUKTU</h2>
        <Form magazynID={magazynID} />
        </Box>
      </Modal>
    </>
  );
}

export {ProductsModal}