package br.furb.resources;

import java.text.ParseException;
import java.util.LinkedList;
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
import br.furb.model.FinancialItem;
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
		Link l = new Link();
		l.setId(link);
		int indice = links.indexOf(l);
		l = links.get(indice);
		try
		{
			// Devido à algum problema no Android, tive que limitar a quantidade de itens financeiros retornados.
			final int limite = 30;
			
			List<FinancialItem> list = client.listItems(l);
			List<FinancialItem> itens = new LinkedList<FinancialItem>();

			for (int i = 0; i < limite; i++)
				itens.add(list.get(i));
			
			return Response.ok(itens).build();
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
