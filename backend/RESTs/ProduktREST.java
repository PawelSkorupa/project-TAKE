package pl.kurs.hurtownia.RESTs;

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

import pl.kurs.hurtownia.Klient;
import pl.kurs.hurtownia.Produkt;
import pl.kurs.hurtownia.EJBs.ProduktEJB;

@Path("/Hurtownia/Produkt")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ProduktREST {

	@EJB
	ProduktEJB bean;
	
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
	public Response create(Produkt produkt)
	{
		bean.create(produkt);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") int id, Produkt produktNew)
	{
		Produkt produkt = bean.get(id);
		if (produkt == null) {
			return Response.status(404).build();
		}
		
		produkt.setNazwa(produktNew.getNazwa());
		produkt.setKategoria(produktNew.getKategoria());
		produkt.setProducent(produktNew.getProducent());
		produkt.setCena(produktNew.getCena());
		
		bean.update(produkt);
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
}
