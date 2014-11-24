package br.furb.avamobile.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.furb.avamobile.R;
import br.furb.avamobile.activities.DadosFinanceiros;


public class ServerMethods
{
	public static String login(String username, String password)
	{
		return null;
	}
	
	private static Date convertDate(String date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = (Date) formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return data;
	}
	
	public static ArrayList<DadosFinanceiros> retornarDadosFinanceiros()
	{
		ArrayList<FinanceiroData> retornoFinanceiro = new ArrayList<>();
		ArrayList<DadosFinanceiros> dados = new ArrayList<>();
		
		retornoFinanceiro = testeTela();
		
		for (FinanceiroData financeiro : retornoFinanceiro) {
			int icone = R.drawable.amarelo;
			
			Date dateVenc = convertDate(financeiro.getDataVencimento());
			Date datePag = convertDate(financeiro.getDataPagamento());
			
			if(financeiro.getDataPagamento().isEmpty()) {
				if(dateVenc.before(new Date(System.currentTimeMillis()))) {
					icone = R.drawable.verelho;
				} else {
					icone = R.drawable.amarelo;
				}
			} else if(datePag.after(dateVenc)) {//Pagamento após o vencimento
				icone = R.drawable.verelho;
			} else if(datePag.before(dateVenc) || datePag.equals(dateVenc)) {
					icone = R.drawable.verde;
			}
			
			ArrayList<String> str = new ArrayList<String>();
			str.add("Convênio: "+financeiro.getConvenio());
			str.add("Valor: "+ financeiro.getValorBruto());
			str.add("Abatimento: "+financeiro.getAbatimento());
			str.add("Dedução: "+financeiro.getDeducoes());
			str.add("Acréscimo:"+financeiro.getAcrescimos());
			str.add("Multa: "+financeiro.getMulta());
			str.add("Data Pagamento:"+financeiro.getDataPagamento());
			str.add("Valor Pago:"+financeiro.getValorPago());
			
			DadosFinanceiros dado = new DadosFinanceiros(financeiro.getDataVencimento(), icone, str);
			dados.add(dado);
			
		}
		return dados;
	}
	
	private static ArrayList<FinanceiroData> testeTela()
	{
		ArrayList<FinanceiroData> list = new ArrayList<>();
		
		FinanceiroData dado1 = new FinanceiroData("30/01/2014", "Senior Sistemas SA", 777.87f, 0.0f, 0.0f, 0.0f, 0.0f, "30/01/2014", 777.87f);
		FinanceiroData dado2 = new FinanceiroData("30/02/2014", "Senior Sistemas SA", 777.87f, 0.0f, 0.0f, 0.0f, 0.0f, "29/02/2014", 777.87f);
		FinanceiroData dado3 = new FinanceiroData("30/03/2014", "Senior Sistemas SA", 777.87f, 0.0f, 0.0f, 0.0f, 0.0f, "01/04/2014", 777.87f);
		FinanceiroData dado4 = new FinanceiroData("30/03/2014", "Senior Sistemas SA", 777.87f, 0.0f, 0.0f, 0.0f, 0.0f, "", 777.87f);
		FinanceiroData dado5 = new FinanceiroData("30/11/2014", "Senior Sistemas SA", 777.87f, 0.0f, 0.0f, 0.0f, 0.0f, "", 777.87f);
		
		list.add(dado1);
		list.add(dado2);
		list.add(dado3);
		list.add(dado4);
		list.add(dado5);
		return list;
		
	}
}
