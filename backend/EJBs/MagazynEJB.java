package pl.kurs.hurtownia.EJBs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pl.kurs.hurtownia.Faktura;
import pl.kurs.hurtownia.Klient;
import pl.kurs.hurtownia.Magazyn;
import pl.kurs.hurtownia.Produkt;


@Stateless
public class MagazynEJB extends EJB<Magazyn> {

	@Override
	public Magazyn get(int id) {
		return manager.find(Magazyn.class, id);
	}

	@Override
	public List<Magazyn> getAll() {
		Query q=manager.createQuery("select m from Magazyn m");
		@SuppressWarnings("unchecked")
		List<Magazyn> list=q.getResultList();
		
//		for (Magazyn m : list) {
//			Query q2=manager.createNativeQuery("select * from Produkt where MAGAZYN_ID=" + m.getId());
//			@SuppressWarnings("unchecked")
//			List<Produkt> produktList=q2.getResultList();
//			m.setProdukty(produktList);
//		}
		
		return list;
	}
	
	public boolean addObject(int id, Produkt object) {
		Magazyn magazyn=get(id);
		if(magazyn==null)
			return false;
		if(magazyn.addProdukt(object))
		{
			manager.persist(object);
			manager.merge(magazyn);
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
		Magazyn magazyn=get(id);
		if(magazyn==null)
			return false;
		Produkt produkt=manager.find(Produkt.class, objectId);
		if(produkt==null)
			return false;
		if(magazyn.removeProdukt(produkt))
		{
			manager.merge(magazyn);
			return true;
		}
		else
			return false;
	}
}
