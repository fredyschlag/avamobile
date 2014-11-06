package br.furb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import br.furb.client.ClientFactory;
import br.furb.client.FurbLogonClient;
import br.furb.login.TokenLogin;
import br.furb.login.TokensController;

import com.sun.jersey.api.client.Client;

@Path("/login")
public class LoginResource {
	
	/**
	 * 
	 * @param user
	 * @param password
	 * @return token
	 * @throws JSONException 
	 */	
	@GET
	@Produces("application/json")
	public String login(@HeaderParam("user") String user, @HeaderParam("password") String password) throws JSONException {
		Client client = ClientFactory.createHTTPClient();		
		TokensController controller = TokensController.getInstance();
		try {
			FurbLogonClient furbClient = new FurbLogonClient(client, user, password);					
			furbClient.logon();				
			TokenLogin token = controller.generateToken(user, password, client);
			JSONObject json = new JSONObject();
			json.put("user", token.getUser());		
			json.put("token", token.getToken());		
			return json.toString();
		} catch (Throwable e) {
			JSONObject json = new JSONObject();
			json.put("error", e.getMessage());				
			return json.toString();
		}
	}
	
}
