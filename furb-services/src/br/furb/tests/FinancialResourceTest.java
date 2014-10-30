package br.furb.tests;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FinancialResourceTest {
	
	@Test
	public void test01() {
		Client client = Client.create();		 
		WebResource webResource = client.resource("http://localhost:8080/furb-services/financial");
		ClientResponse response = webResource.accept("application/json").header("token", "10000").get(ClientResponse.class);
 
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
 
		String output = response.getEntity(String.class);
 
		System.out.println("Output from Server .... \n");
		System.out.println(output); 
	}	

}
