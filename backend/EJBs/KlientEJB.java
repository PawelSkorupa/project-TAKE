package pl.kurs.hurtownia.EJBs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pl.kurs.hurtownia.Faktura;
import pl.kurs.hurtownia.Klient;


@Stateless
public class KlientEJB extends EJB<Klient> {

	@Override
	public Klient get(int id) {
		return manager.find(Klient.class, id);
	}

	@Override
	public List<Klient> getAll() {
		Query q=manager.createQuery("select k from Klient k");
		@SuppressWarnings("unchecked")
		List<Klient> list=q.getResultList();
		return list;
	}
	
	public boolean addObject(int id, Faktura object) {
		Klient klient=get(id);
		if(klient==null)
			return false;
		if(klient.addFaktura(object))
		{
			manager.persist(object);
			manager.merge(klient);
			return true;
		}
		else
			return false;
	}
	
	public boolean addObject(int id, int objectId) {
		Faktura faktura=manager.find(Faktura.class, objectId);
		if(faktura==null)
			return false;
		if(addObject(id, faktura))
			return true;
		else
			return false;
	}

	public boolean removeObject(int id, int objectId) {
		Klient klient=get(id);
		if(klient==null)
			return false;
		Faktura faktura=manager.find(Faktura.class, objectId);
		if(faktura==null)
			return false;
		if(klient.removeFaktura(faktura))
		{
			manager.merge(klient);
			return true;
		}
		else
			return false;
	}
}
