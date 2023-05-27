package pl.kurs.hurtownia.RESTs;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import pl.kurs.hurtownia.Faktura;
import pl.kurs.hurtownia.Klient;
import pl.kurs.hurtownia.Magazyn;
import pl.kurs.hurtownia.Produkt;
import pl.kurs.hurtownia.EJBs.MagazynEJB;

@Path("/Hurtownia/Magazyn")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class MagazynREST {

	@EJB
	MagazynEJB bean;
	
	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") int id)
	{
		if(bean.get(id)==null)
			return Response.status(404).build();
		else
			return Response.ok(bean.get(id)).build();
	} 
	
	@GET
	public Response getAll()
	{
		if(bean.getAll().size()==0)
			return Response.status(204).build();
		else
			return Response.ok(bean.getAll()).build();
	}
	
	@POST
	public Response create(Magazyn magazyn)
	{
		bean.create(magazyn);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") int id, Magazyn magazynNew)
	{
		Magazyn magazyn = bean.get(id);
		if (magazyn == null) {
			return Response.status(404).build();
		}
		
		magazyn.setAdres(magazynNew.getAdres());
		magazyn.setKierownik(magazynNew.getKierownik());
		
		bean.update(magazyn);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id)
	{
		if(bean.delete(id))
			return Response.ok().build();
		else
			return Response.status(404).build();
	}
	
	// =================
	// obsluga produktow
	// =================
	
	@GET
	@Path("/{id}/Produkty")
	public Response getProdukty(@PathParam("id") int id) 
	{
	    Magazyn magazyn = bean.get(id);
	    if(magazyn==null)
	    	return Response.status(404).build();
	    Set<Produkt> set = magazyn.getProdukty();
	    if(set.size()==0)
	    	return Response.status(204).build();
	    else
	    	return Response.ok(set).build();
	}

	@POST
	@Path("/{id}/Produkt")
	public Response addProdukt(@PathParam("id") int id, Produkt produkt) 
	{
	    if(bean.addObject(id, produkt))
	    	return Response.ok().build();
	    else
	    	return Response.status(404).build();
	}

	@PUT
	@Path("/{id}/Produkt/{produktId}")
	public Response updateProduktInMagazyn(@PathParam("id") int id, @PathParam("produktId") int produktId)
	{
		if(bean.addObject(id, produktId))
			return Response.ok().build();
		else
			return Response.status(404).build();
	}

	@DELETE
	@Path("/{id}/Produkt/{produktId}")
	public Response deleteProduktFromMagazyn(@PathParam("id") int id, @PathParam("produktId") int produktId)
	{
		if(bean.removeObject(id,produktId))
			return Response.ok().build();
		else
			return Response.status(404).build();
	}
}