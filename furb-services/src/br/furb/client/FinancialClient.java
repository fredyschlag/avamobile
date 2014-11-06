package br.furb.client;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import br.furb.model.DetailsFinancialItem;
import br.furb.model.FinancialItem;
import br.furb.model.Link;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class FinancialClient {
	
	private Client client;
	
	public FinancialClient(Client client) {
		this.client = client;
	}		
	
	public List<Link> listLinks() {		
		WebResource webResource = client.resource("https://www.furb.br/academico/uFinanca");
		ClientResponse response = webResource.get(ClientResponse.class);
		String html = ClientUtils.logResponse(response);
		return FinancialUtils.parseLinks(html);
	}
	
	public List<FinancialItem> listItems(Link link) throws ParseException {
		WebResource webResource = client.resource("https://www.furb.br/academico/consaFinanca");		
		Form form = new Form();
		form.putSingle("vinculo", link.getId());
		form.putSingle("nome", link.getName());
		form.putSingle("curso", link.getCourse());
		form.putSingle("ds_vinculo", link.getDescription());
		
		ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, form);
		String html = ClientUtils.logResponse(response);
		return FinancialUtils.parseItems(html);
	}
	
	public List<DetailsFinancialItem> listDetails(Link link, FinancialItem item) throws ParseException {
		WebResource webResource = client.resource("https://www.furb.br/academico/consaDetalheBloquete");
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("numeroBloquete", item.getDetailsId());
		params.add("vinculo", link.getId());
		params.add("nome", link.getName());
		params.add("curso", link.getCourse());
		params.add("ds_vinculo", link.getDescription()); 
		ClientResponse response = webResource.queryParams(params).get(ClientResponse.class);
		String html = ClientUtils.logResponse(response);
		return FinancialUtils.parseDetailsItem(html);
	}
				
	public static void main(String[] args) {
		Client client = Client.create();
		
		client.addFilter(new ClientFilter() {
		    private ArrayList<Object> cookies;

		    @Override
		    public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
		        if (cookies != null) {
		            request.getHeaders().put("Cookie", cookies);
		        }
		        ClientResponse response = getNext().handle(request);
		        // copy cookies
		        if (response.getCookies() != null) {
		            if (cookies == null) {
		                cookies = new ArrayList<Object>();
		            }
		            // A simple addAll just for illustration (should probably check for duplicates and expired cookies)
		            cookies.addAll(response.getCookies());
		        }
		        return response;
		    }
		});
		
		client.setFollowRedirects(false);
		WebResource webResource = client.resource("https://www.furb.br/academico/uFinanca");
		ClientResponse response = webResource.get(ClientResponse.class);
		String output = response.getEntity(String.class);
		System.out.println(response.toString());
		System.out.println("Output from Server .... \n");
		System.out.println(output);				
		
		webResource = client.resource("https://www.furb.br/academico/validaLogon");		
		Form form = new Form();
		form.putSingle("nm_login", "USUARIO");
		form.putSingle("ds_senha", "SENHA");
		form.putSingle("nome_servlet", "/academico/uFinanca");		
		response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, form);				
		System.out.println(response.toString());
		output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n");
		System.out.println(output);				
		
		webResource = client.resource("https://www.furb.br/academico/uFinanca");
		response = webResource.get(ClientResponse.class);
		output = response.getEntity(String.class);
		System.out.println(response.toString());
		System.out.println("Output from Server .... \n");
		System.out.println(output);		
		
		webResource = client.resource("https://www.furb.br/academico/consaFinanca");		
		form = new Form();
		form.putSingle("vinculo", "80707");
		form.putSingle("nome", "Fredy+Schlag");
		form.putSingle("curso", "Ci%EAncia+da+Computa%E7%E3o");
		form.putSingle("ds_vinculo", "Graduacao");
		
		response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, form);
		System.out.println(response.toString());
		output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n");
		System.out.println(output);
		
		webResource = client.resource("https://www.furb.br/academico/consaDetalheBloquete");
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("numeroBloquete", "4386438");
		params.add("vinculo", "80707");
		params.add("nome", "Fredy+Schlag");
		params.add("curso", "Ci%EAncia+da+Computa%E7%E3o");
		params.add("ds_vinculo", "Graduacao"); 
		response = webResource.queryParams(params).get(ClientResponse.class);
		output = response.getEntity(String.class);
		System.out.println(response.toString());
		System.out.println("Output from Server .... \n");
		System.out.println(output);						
	}

}
