package pl.kurs.hurtownia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Faktura {
	
	int id;
	double cenaCalkowita;
	Set<Produkt> produkty = new HashSet<Produkt>();
	
	@Id
	@GeneratedValue
	@XmlAttribute
	public int getId() {
		return id;
	}
	
	public double getCenaCalkowita() {
		return cenaCalkowita;
	}
	
	public void setCenaCalkowita(double cenaCalkowita) {
		this.cenaCalkowita = cenaCalkowita;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "faktura_id", referencedColumnName="id")
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
