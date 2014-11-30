package br.furb.avamobile.core;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import br.furb.avamobile.core.ServerRequest.ServerRequestListener;

public class FinanceiroDataRequest implements ServerRequestListener
{	
	final String FINANCEIRO_ITENS_URL = ServerUtils.SERVER_URL + "financial/items";
	
	Context context;
	ProgressDialog progress;	
	FinanceiroDataListener listener;
	
	public FinanceiroDataRequest(Context context, FinanceiroDataListener listener)
	{
		this.listener = listener;
		this.context = context;
		this.progress = new ProgressDialog(context);
		this.progress.setIndeterminate(true);
		this.progress.setCancelable(false);
		this.progress.setTitle("Aguarde");
		this.progress.setMessage("Processando...");
	}
	
	public void execute(String token, FinanceiroLink link)
	{
		NameValuePair kpToken = new BasicNameValuePair("token", token);
		NameValuePair kpLink = new BasicNameValuePair("link", link.getId());
		try
		{
			ServerRequest itensTask = new ServerRequest(context, FINANCEIRO_ITENS_URL, this);
			itensTask.execute(kpToken, kpLink);
		}
		catch (Exception ex)
		{
			listener.error(ex.getMessage());
		}
	}
	
	@Override
	public void onRequestStart()
	{
		progress.show();	
	}

	@Override
	public void onRequestComplete(String response, boolean error)
	{
		progress.dismiss();
		
		if (!error)
		{
			try
			{	
				try
				{
					JSONObject json = new JSONObject(response);
					
					if ((!error) && (json.has("error")))
						throw new Exception(json.getString("error"));
				}
				catch (Exception ex)
				{
					// Se houver erro é porque é um JSONArray e nao é erro.
				}
				
				JSONArray jsonItens = new JSONArray(response);
				
				List<FinanceiroData> dados = new LinkedList<FinanceiroData>();
				
				for (int i = 0; i < jsonItens.length(); i++)
				{
					JSONObject joItem = jsonItens.getJSONObject(i);
					
					FinanceiroData data = new FinanceiroData();
					
					Date vecto = null;
					Date pagto = null;
					
					if (!joItem.isNull("dueDate"))					
						vecto = new Date(joItem.getLong("dueDate"));
					
					if (!joItem.isNull("datePayment"))					
						pagto = new Date(joItem.getLong("datePayment")); 
					
					data.setDataVencimento(vecto);
					data.setConvenio(joItem.getString("convenant"));
					data.setValorBruto(joItem.getDouble("grossValue"));
					data.setAbatimento(joItem.getDouble("discount"));
					data.setDeducoes(joItem.getDouble("deductions"));
					data.setAcrescimos(joItem.getDouble("increase"));
					data.setMulta(joItem.getDouble("fine"));
					data.setDataPagamento(pagto);
					data.setValorPago(joItem.getDouble("amountPaid"));
					
					dados.add(data);
				}
				
				listener.sucess(dados);
			}
			catch (Exception ex)
			{
				error = true;
				response = ex.getMessage();
			}
		}
		
		if (error) listener.error(response);
	}
	
	public interface FinanceiroDataListener
	{
		void sucess(List<FinanceiroData> dados) throws Exception;
		void error(String error);
	}
}
