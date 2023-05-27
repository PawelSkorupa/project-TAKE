package pl.kurs.hurtownia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Magazyn {
	
	int id;
	String adres;
	String kierownik;
	Set<Produkt> produkty = new HashSet<Produkt>();
	
	@Id
	@GeneratedValue
	@XmlAttribute
	public int getId() {
		return id;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	public String getKierownik() {
		return kierownik;
	}
	
	public void setKierownik(String kierownik) {
		this.kierownik = kierownik;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="magazyn_id", referencedColumnName="id")
	public Set<Produkt> getProdukty() {
		return produkty;
	}
	
	public boolean addProdukt(Produkt value)
	{
		for(Produkt produkt : produkty)
		{
			if(produkt.id==value.id)
			{
				return false;
			}
		}
		produkty.add(value);
		return true;
	}
	public boolean removeProdukt(Produkt value)
	{
		return produkty.remove(value);
	}
	
	public void setProdukty(Set<Produkt> produkty) {
		this.produkty = produkty;
	}
	
}
