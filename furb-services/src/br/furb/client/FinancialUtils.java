package br.furb.client;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.furb.model.Link;

public class FinancialUtils {
	
	private static Elements getElements(Document document, String name) {
		return document.getElementsByAttributeValue("NAME", name);
	}	
	
	public static List<Link> parseLinks(String html) {
		Document document = Jsoup.parse(html);
		Elements links = getElements(document, "vinculo");
		Elements names = getElements(document, "nome");
		Elements courses = getElements(document, "curso");
		Elements linksDescriptions = getElements(document, "ds_vinculo");
		
		List<Link> linkList = new ArrayList<Link>();
		
		for (int i = 0; i < links.size(); i++) {
			Link link = new Link(Integer.valueOf(links.get(i).val()));
			link.setName(names.get(i).val());
			link.setCourse(courses.get(i).val());
			link.setDescription(linksDescriptions.get(i).val());
			linkList.add(link);
		}
		
		return linkList;		
	}

}
