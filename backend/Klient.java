package pl.kurs.hurtownia;

import java.io.Serializable;
import java.util.HashSet;
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
public class Klient {
	
	int id;
	String imie;
	String nazwisko;
	String adres;
	String numerTelefonu;
	Set<Faktura> faktury = new HashSet<Faktura>();
	
	@Id
	@GeneratedValue
	@XmlAttribute
	public int getId() {
		return id;
	}
	
	public String getImie() {
		return imie;
	}
	
	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}
	
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	public String getNumerTelefonu() {
		return numerTelefonu;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNumerTelefonu(String numerTelefonu) {
		this.numerTelefonu = numerTelefonu;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="klient_id", referencedColumnName="id")
	public Set<Faktura> getFaktury() {
		return faktury;
	}
	
	public boolean addFaktura(Faktura value)
	{
		for(Faktura faktura : faktury)
		{
			if(faktura.id==value.id)
			{
				return false;
			}
		}
		faktury.add(value);
		return true;
	}
	public boolean removeFaktura(Faktura value)
	{
		return faktury.remove(value);
	}
	
	public void setFaktury(Set<Faktura> faktury) {
		this.faktury = faktury;
	}
	
}
