package pl.kurs.hurtownia.EJBs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pl.kurs.hurtownia.Faktura;
import pl.kurs.hurtownia.Produkt;

@Stateless
public class FakturaEJB extends EJB<Faktura> {

	@Override
	public Faktura get(int id) {
		return manager.find(Faktura.class, id);
	}

	@Override
	public List<Faktura> getAll() {
		Query q=manager.createQuery("select f from Faktura f");
		@SuppressWarnings("unchecked")
		List<Faktura> list=q.getResultList();
		return list;
	}
	
	public boolean addObject(int id, Produkt object) {
		Faktura faktura=get(id);
		if(faktura==null)
			return false;
		if(faktura.addProdukt(object))
		{
			faktura.setCenaCalkowita(faktura.getCenaCalkowita() + object.getCena());
			manager.persist(object);
			manager.merge(faktura);
			return true;
		}
		else
			return false;
	}
	
	public boolean addObject(int id, int objectId) {
		Produkt produkt=manager.find(Produkt.class, objectId);
		if(produkt==null)
			return false;
		if(addObject(id, produkt))
			return true;
		else
			return false;
	}

	public boolean removeObject(int id, int objectId) {
		Faktura faktura=get(id);
		if(faktura==null)
			return false;
		Produkt produkt=manager.find(Produkt.class, objectId);
		if(produkt==null)
			return false;
		if(faktura.removeProdukt(produkt))
		{
			faktura.setCenaCalkowita(faktura.getCenaCalkowita() - produkt.getCena());
			manager.merge(faktura);
			return true;
		}
		else
			return false;
	}
}
