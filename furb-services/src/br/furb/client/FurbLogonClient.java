package br.furb.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class FurbLogonClient {
	
	private Client client;
	private String user;
	private String password;
	
	public FurbLogonClient(Client client, String user, String password) {
		this.client = client;
		this.user = user;
		this.password = password;
	}
	
	public void logon() {
		WebResource webResource = client.resource("https://www.furb.br/academico/servicosAcademicos");
		ClientResponse response = webResource.get(ClientResponse.class);
		ClientUtils.logResponse(response);		
		
		webResource = client.resource("https://www.furb.br/academico/validaLogon");		
		Form form = new Form();
		form.putSingle("nm_login", user);
		form.putSingle("ds_senha", password);
		//form.putSingle("nome_servlet", "/academico/uFinanca");		
		response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, form);
		ClientUtils.logResponse(response);
		//TODO se ocorreu falha, lançar exceção
	}

}
