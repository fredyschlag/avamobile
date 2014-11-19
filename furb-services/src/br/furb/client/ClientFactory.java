package br.furb.client;

import java.util.ArrayList;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public class ClientFactory {
	
	public static Client createHTTPClient() 
	{	
		Client client = Client.create();
		
		client.addFilter(new ClientFilter()
		{
		    private ArrayList<Object> cookies;

		    @Override
		    public ClientResponse handle(ClientRequest request) throws ClientHandlerException 
		    {
		        if (cookies != null)
		            request.getHeaders().put("Cookie", cookies);
		        
		        ClientResponse response = getNext().handle(request);
		        
		        // copy cookies
		        if (response.getCookies() != null) 
		        {
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
		return client;
	}

}
