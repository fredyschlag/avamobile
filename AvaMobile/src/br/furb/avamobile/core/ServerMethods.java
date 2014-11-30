package br.furb.avamobile.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.text.format.DateFormat;
import br.furb.avamobile.R;
import br.furb.avamobile.activities.DadosFinanceiros;


public class ServerMethods
{	
	public static ArrayList<DadosFinanceiros> montarDadosFinanceiros(List<FinanceiroData> retornoFinanceiro) throws Exception
	{
		ArrayList<DadosFinanceiros> dados = new ArrayList<>();
		
		for (FinanceiroData financeiro : retornoFinanceiro) 
		{
			int icone = R.drawable.amarelo;
			
			if (financeiro.getDataPagamento() == null)
			{
				if (financeiro.getDataVencimento().before(new Date(System.currentTimeMillis())))
					icone = R.drawable.vermelho;
				else
					icone = R.drawable.amarelo;
			}
			else
				icone = R.drawable.verde; // Pago é pago
			
			String pagto = "";
			if (financeiro.getDataPagamento() != null)
				pagto = DateFormat.format("dd/MM/yyyy", financeiro.getDataPagamento()).toString();
			
			String vecto = "";
			if (financeiro.getDataVencimento() != null)
				vecto = DateFormat.format("dd/MM/yyyy", financeiro.getDataVencimento()).toString();
			
			ArrayList<String> str = new ArrayList<String>();
			
			str.add("Convênio: " + financeiro.getConvenio());
			str.add("Valor: " + financeiro.getValorBruto());
			str.add("Abatimento: " + financeiro.getAbatimento());
			str.add("Deduções: " + financeiro.getDeducoes());
			str.add("Acréscimos: " + financeiro.getAcrescimos());
			str.add("Multa: " + financeiro.getMulta());
			str.add("Data Pagamento: " + pagto);
			str.add("Valor Pago: " + financeiro.getValorPago());
						
			DadosFinanceiros dado = new DadosFinanceiros(vecto, icone, str);
			
			dados.add(dado);
		}
		return dados;
	}
	
	/*
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
	*/
}
