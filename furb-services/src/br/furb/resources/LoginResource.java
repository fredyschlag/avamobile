package br.furb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

@Path("/login")
public class LoginResource {
	
	/**
	 * 
	 * @param user
	 * @param password
	 * @return token
	 */	
	@GET
	@Produces("application/json")
	public String login(@HeaderParam("user") String user, @HeaderParam("password") String password) {
		JSONObject json = new JSONObject();
		json.put("user", user);		
		json.put("token", (user.concat(password)).hashCode());
		return json.toString();
	}
	
}
