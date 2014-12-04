package br.furb.resources;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.furb.client.FinancialClient;
import br.furb.login.TokenLogin;
import br.furb.login.TokensController;
import br.furb.model.Link;

@Path("/financial")
public class FinancialResource {
	
	@Path("/items")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFinancialItems(@HeaderParam("token") String token, @HeaderParam("link") String link) 
	{
		TokenLogin tokenLogin = TokensController.getInstance().getToken(token);
		FinancialClient client = new FinancialClient(tokenLogin.getClient());
		List<Link> links = client.listLinks();
		try
		{			
			Link l = new Link();
			l.setId(link);
			int indice = links.indexOf(l);
			l = links.get(indice);
			
			return Response.ok(client.listItems(l)).build();
		} 
		catch (ParseException e)
		{
			return Response.serverError().build();
		}
	}
	
	@Path("/links")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLinks(@HeaderParam("token") String token) 
	{
		TokenLogin tokenLogin = TokensController.getInstance().getToken(token);
		FinancialClient client = new FinancialClient(tokenLogin.getClient());
		return Response.ok(client.listLinks()).build();
	}

}
