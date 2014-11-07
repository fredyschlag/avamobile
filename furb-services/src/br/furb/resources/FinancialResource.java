package br.furb.resources;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import br.furb.client.FinancialClient;
import br.furb.login.TokenLogin;
import br.furb.login.TokensController;
import br.furb.model.FinancialItem;
import br.furb.model.Link;

@Path("/financial")
public class FinancialResource {
	
	@Path("/items")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<FinancialItem> getFinancialItems(@HeaderParam("token") String token, Link link) throws ParseException {
		TokenLogin tokenLogin = TokensController.getInstance().getToken(token);
		FinancialClient client = new FinancialClient(tokenLogin.getClient());
		return client.listItems(link);
	}
	
	@Path("/links")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Link> getLinks(@HeaderParam("token") String token) {
		TokenLogin tokenLogin = TokensController.getInstance().getToken(token);
		FinancialClient client = new FinancialClient(tokenLogin.getClient());
		return client.listLinks();
	}
	

}
