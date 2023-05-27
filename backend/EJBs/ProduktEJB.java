package pl.kurs.hurtownia.EJBs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pl.kurs.hurtownia.Produkt;

@Stateless
public class ProduktEJB extends EJB<Produkt> {
	
	public Produkt get(int id)
	{
		return manager.find(Produkt.class,id);
	}
	
	public List<Produkt> getAll()
	{
		Query q=manager.createQuery("select p from Produkt p");
		@SuppressWarnings("unchecked")
		List<Produkt> list=q.getResultList();
		return list;
	}
}
