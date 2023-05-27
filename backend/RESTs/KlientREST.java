package pl.kurs.hurtownia.RESTs;

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
import pl.kurs.hurtownia.EJBs.KlientEJB;

@Path("/Hurtownia/Klient")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class KlientREST {

	@EJB
	KlientEJB bean;
	
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
	public Response create(Klient klient)
	{
		bean.create(klient);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") int id, Klient klientNew)
	{
		Klient klient = bean.get(id);
		if (klient == null) {
			return Response.status(404).build();
		}
		
		klient.setImie(klientNew.getImie());
		klient.setNazwisko(klientNew.getNazwisko());
		klient.setAdres(klientNew.getAdres());
		klient.setNumerTelefonu(klientNew.getNumerTelefonu());
		
		bean.update(klient);
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
	
	// ==============
	// obsluga faktur
	// ==============
	
	@GET
	@Path("/{id}/Faktury")
	public Response getProdukty(@PathParam("id") int id) 
	{
	    Klient klient = bean.get(id);
	    if(klient==null)
	    	return Response.status(404).build();
	    Set<Faktura> set = klient.getFaktury();
	    if(set.size()==0)
	    	return Response.status(204).build();
	    else
	    	return Response.ok(set).build();
	}

	@POST
	@Path("/{id}/Faktura")
	public Response addFaktura(@PathParam("id") int id, Faktura faktura) 
	{
	    if(bean.addObject(id, faktura))
	    	return Response.ok().build();
	    else
	    	return Response.status(404).build();
	}

	@PUT
	@Path("/{id}/Faktura/{fakturaId}")
	public Response updateFakturaInKlient(@PathParam("id") int id, @PathParam("fakturaId") int fakturaId)
	{
		if(bean.addObject(id, fakturaId))
			return Response.ok().build();
		else
			return Response.status(404).build();
	}

	@DELETE
	@Path("/{id}/Faktura/{fakturaId}")
	public Response deleteFakturaFromKlient(@PathParam("id") int id, @PathParam("fakturaId") int fakturaId)
	{
		if(bean.removeObject(id,fakturaId))
			return Response.ok().build();
		else
			return Response.status(404).build();
	}
}