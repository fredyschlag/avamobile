package br.furb.tests;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import br.furb.client.ClientFactory;
import br.furb.client.FinancialClient;
import br.furb.client.FurbLogonClient;
import br.furb.model.FinancialItem;
import br.furb.model.Link;

import com.sun.jersey.api.client.Client;

public class FinancialClientTest {
	
	public static void main(String[] args) throws IOException, ParseException {		
		Client client = ClientFactory.createHTTPClient();
		FurbLogonClient logon = new FurbLogonClient(client, args[0], args[1]);
		logon.logon();
		FinancialClient financial = new FinancialClient(client);
		List<Link> links = financial.listLinks();
		Link link = links.get(0);
		List<FinancialItem> financialItems = financial.listItems(link);				
		financial.listDetails(link, financialItems.get(0));
	}

}
