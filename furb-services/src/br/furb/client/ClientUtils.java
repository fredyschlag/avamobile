package br.furb.client;

import com.sun.jersey.api.client.ClientResponse;

public class ClientUtils {
	
	public static String logResponse(ClientResponse response) {
		String output = response.getEntity(String.class);
		System.out.println("Response: " + response.toString());
		System.out.println("Output from server:\n" + output);
		return output;
	}

}
