package br.furb.client;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.furb.model.FinancialItem;
import br.furb.model.Link;

public class FinancialUtils {

	private static Elements getElements(Document document, String name) {
		return document.getElementsByAttributeValue("NAME", name);
	}

	public static List<Link> parseLinks(Document document) {
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

	public static List<Link> parseLinks(String html) {
		Document document = Jsoup.parse(html);
		return parseLinks(document);
	}

	private static String getStringValue(Elements elements, int index) {
		return elements.get(index).html();
	}
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static NumberFormat decimalFormat = DecimalFormat.getInstance(new Locale("pt", "BR"));

	private static double getDoubleValue(Elements elements, int index) throws ParseException {
		String value = getStringValue(elements, index);
		return decimalFormat.parse(value).doubleValue();
	}

	private static Date getDateValue(Elements elements, int index) throws ParseException {
		String value = getStringValue(elements, index);
		if (value.isEmpty()) {
			return null;
		}
		
		return dateFormat.parse(value);
	}

	public static List<FinancialItem> parseItems(Document document)
			throws ParseException {
		Elements tables = document.getElementsByAttributeValue("class",
				"bodyTable");
		Element table = tables.get(1);
		Elements rows = table.getElementsByTag("tr");
		List<FinancialItem> list = new ArrayList<>();

		for (int i = 1; i < rows.size(); i++) {
			Element row = rows.get(i);
			Elements cols = row.getElementsByTag("td");
			FinancialItem item = new FinancialItem();
			item.setDueDate(getDateValue(cols, 0));
			item.setConvenant(getStringValue(cols, 1));
			item.setGrossValue(getDoubleValue(cols, 2));
			item.setDiscount(getDoubleValue(cols, 3));
			item.setDeductions(getDoubleValue(cols, 4));
			item.setIncrease(getDoubleValue(cols, 5));
			item.setFine(getDoubleValue(cols, 6));
			item.setDatePayment(getDateValue(cols, 7));
			item.setAmountPaid(getDoubleValue(cols, 8));
			
			//TODO consultar detalhes
			list.add(item);
		}

		return list;
	}
	
	public static List<FinancialItem> parseItems(String html) throws ParseException {
		Document document = Jsoup.parse(html);
		return parseItems(document);
	}

}
