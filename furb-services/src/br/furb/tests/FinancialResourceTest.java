package br.furb.tests;

import javax.xml.bind.JAXBException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import br.furb.model.Link;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class FinancialResourceTest {

	@Test
	public void test01() {
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:8080/furb-services/financial");
		ClientResponse response = webResource.accept("application/json")
				.header("token", "10000").get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}

	public static void main(String[] args) throws JSONException, JAXBException {
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:8080/furb-services/login");
		ClientResponse response = webResource.accept("application/json")
				.header("user", args[0]).header("password", args[1])
				.get(ClientResponse.class);
		String json = response.getEntity(String.class);
		JSONObject object = new JSONObject(json);
		String token = object.getString("token");

		webResource = client
				.resource("http://localhost:8080/furb-services/financial/links");
		response = webResource.accept("application/json")
				.header("token", token).get(ClientResponse.class);
		json = response.getEntity(String.class);
		JSONArray array = new JSONArray(json);

		webResource = client
				.resource("http://localhost:8080/furb-services/financial/items");
		object = (JSONObject) array.get(0);
		Link link = new Link();
		link.setCourse(object.getString("course"));
		link.setDescription(object.getString("description"));
		link.setId(object.getString("id"));
		link.setName(object.getString("name"));
	}

}
