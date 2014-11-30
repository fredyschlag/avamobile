package br.furb.avamobile.core;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import br.furb.avamobile.core.ServerRequest.ServerRequestListener;

public class FinanceiroRequest implements ServerRequestListener
{	
	final String FINANCEIRO_LINKS_URL = ServerUtils.SERVER_URL + "financial/links";
	final String FINANCEIRO_ITENS_URL = ServerUtils.SERVER_URL +  "financial/items";
	
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
	
	public void links(String token)
	{
		NameValuePair kpToken = new BasicNameValuePair("token", token);	
		try
		{
			ServerRequest linksTask = new ServerRequest(context, FINANCEIRO_LINKS_URL, this);		
			linksTask.execute(kpToken);
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
				JSONObject json = new JSONObject(response);
				
				if ((!error) && (json.has("error")))
					throw new Exception(json.getString("error"));
				
				JSONArray jsonLinks = new JSONArray(response);
				
				List<FinanceiroLink> links = new LinkedList<FinanceiroLink>();
				
				for (int i = 0; i < jsonLinks.length(); i++)
				{
					JSONObject joLink = jsonLinks.getJSONObject(i);
					
					FinanceiroLink link = new FinanceiroLink();
					link.setId(joLink.getString("id"));
					link.setName(joLink.getString("name"));
					link.setDescription(joLink.getString("description"));
					link.setCourse(joLink.getString("course"));
					
					links.add(link);
				}				
				
				listener.sucess(links);
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
		void sucess(List<FinanceiroLink> links);
		void error(String error);
	}
}
