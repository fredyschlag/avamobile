package br.furb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class HelloWorldResource {

	public String showHelloWorld() {
		return "Olá mundo!";
	}

	@GET
	@Produces("application/json")
	public String getBandas() {
		return "Olá";		
	}

}
