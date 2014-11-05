package br.furb.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.furb.client.ClientFactory;
import br.furb.client.FinancialClient;
import br.furb.client.FurbLogonClient;
import br.furb.model.Link;

import com.sun.jersey.api.client.Client;

public class FinancialClientTest {
	
	public static void main(String[] args) throws IOException {
		Document document = Jsoup.parse(new File("C:\\temp\\furb.hmtl"), "iso-8859-1");
		document.getElementsByAttributeValue("class", "bodyTable");
		
		
		
		Client client = ClientFactory.createHTTPClient();
		FurbLogonClient logon = new FurbLogonClient(client, args[0], args[1]);
		logon.logon();
		FinancialClient financial = new FinancialClient(client);
		List<Link> links = financial.listLinks();
		financial.listItems(links.get(0));		
		
	}

}
