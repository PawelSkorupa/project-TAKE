package pl.kurs.hurtownia;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Produkt {
	
	int id;
	String nazwa;
	String kategoria;
	String producent;
	double cena;
	
	@Id
	@GeneratedValue
	@XmlAttribute
	public int getId() {
		return id;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getKategoria() {
		return kategoria;
	}
	
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	
	public String getProducent() {
		return producent;
	}
	
	public void setProducent(String producent) {
		this.producent = producent;
	}
	
	public double getCena() {
		return cena;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
}
