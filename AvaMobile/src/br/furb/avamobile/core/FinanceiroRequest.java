package br.furb.avamobile.core;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import br.furb.avamobile.core.ServerRequest.ServerRequestListener;

public class FinanceiroRequest implements ServerRequestListener
{
	final String FINANCEIRO_URL = "http://192.168.56.1:8080/furb-services/financial";
	
	Context context;
	ProgressDialog progress;	
	FinanceiroListener listener;
	
	public FinanceiroRequest(Context context)
	{
		this.context = context;
		this.progress = new ProgressDialog(context);
		this.progress.setIndeterminate(true);
		this.progress.setCancelable(false);
		this.progress.setTitle("Aguarde");
		this.progress.setMessage("Processando...");
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
				JSONObject json = new JSONObject(response);
				
				if ((!error) && (json.has("error")))
					throw new Exception(json.getString("error"));
				
				listener.sucess(json.getString("financeiro"));
			}
			catch (Exception ex)
			{
				error = true;
				response = ex.getMessage();
			}
		}
		
		if (error) listener.error(response);
	}
	
	public interface FinanceiroListener
	{
		void sucess(String dadosFinanceiros);
		void error(String error);
	}
}
