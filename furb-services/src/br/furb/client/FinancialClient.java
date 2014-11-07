package br.furb.client;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import br.furb.model.DetailsFinancialItem;
import br.furb.model.FinancialItem;
import br.furb.model.Link;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
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

}
