"use client"

import { useState, useEffect } from "react"

import styles from './page.module.css'
import { ProductsModal } from '../../components/productsModal.js'
import { MagazynForm } from '../../components/magazynForm.js'
import { UpdateModal } from "../../components/UpdateModal"


export default function Home() {
  const [data, setData] = useState([]);

  const fetchData = () => {
    fetch(`http://localhost:8080/take/Hurtownia/Magazyn`)
      .then((response) => response.json())
      .then((actualData) => {
        setData(actualData)
        console.log(actualData)
      })
      .catch((err) => {
        console.log(err.message);
      });
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleDelete = (magazynID) => {
    try {
        fetch(`http://localhost:8080/take/Hurtownia/Magazyn/${magazynID}`, {
            method: "DELETE",
            mode: "cors",
        })
        window.location.reload()
    } catch (error) {
        console.error(error);
    }
  }


  return (
    <main className={styles.main}>
        <h1 className={styles.text}>HURTOWNIA TAKE</h1>
        <h3 className={styles.text}>MAGAZYNY</h3>
        <table className={styles.table}>
          <tbody>
            <tr className={styles.table}>
              <th className={styles.table}>id</th>
              <th className={styles.table}>adres</th>
              <th className={styles.table}>kierownik</th>
              <th className={styles.table}>akcje</th>
            </tr>
            {data.map((item, index) => (
              <tr className={styles.table} key={index}>
                <td className={styles.table}>{item.id}</td>
                <td className={styles.table}>{item.adres}</td>
                <td className={styles.table}>{item.kierownik}</td>
                <td className={styles.table}>
                  <ProductsModal magazynID={item.id} />
                  <UpdateModal magazynID={item.id}/>
                  <button className={styles.Button} onClick={() => handleDelete(item.id)}>Usuń</button>
                </td>
              </tr>
            ))}
          </tbody>
      </table>
      <MagazynForm/>
    </main>
  )
}
