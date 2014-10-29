package br.furb.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.furb.model.DetailsFinancialItem;
import br.furb.model.FinancialItem;

@Path("/financial")
public class FinancialResource {
	
	@GET
	@Produces("application/json")
	public List<FinancialItem> getFinancialItems() {
		List<FinancialItem> items = new ArrayList<>();
		
		/*
		 * Exemplo de dados 
		 */
		for (int i = 0; i < 2; i++) {
			List<DetailsFinancialItem> listDetails = new ArrayList<>();
			
			for (int j = 0; j < 2; j++) {
				DetailsFinancialItem details = new DetailsFinancialItem();
				details.setDescription("Description " + i);
				details.setDeductions(i / 2);
				details.setDiscount(i / 2);
				details.setFine(i / 2);
				details.setGrossValue(i / 2);
				details.setIncrease(i / 2);
				details.setIssueDate(new Date());
				listDetails.add(details);
			}
			
			FinancialItem item = new FinancialItem();
			item.setAmountPaid(i / 2);
			item.setConvenant("Conenant " + i);
			item.setDatePayment(new Date());
			item.setDeductions(i / 2);
			item.setDetails(listDetails);			
		}
		
		return items;
	}
	

}
